package lab9;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LettersUI extends Application {

	final static int SCENE_WIDTH = 300;
	final static int SCENE_HEIGHT = 150;
	
	boolean showing = false;
	String prev = "";
	
	public static void main(String[] args) {
		launch(LettersUI.class, args);
	}

	@Override
	public void start(Stage stage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes

		String[] string_thread = { "A", "B", "C", "D", "E" };
		for (String s : string_thread) {
			Text text = new Text(s);
			text.setFont(new Font(50));
			text.setVisible(false);
			hbox.getChildren().addAll(text);
		}
		
		Scene scene = new Scene(hbox, SCENE_WIDTH, SCENE_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Example");
		stage.show();

		// Since now you have to add 3 more Threads, Can we use a for loop instead ?
		for (int i=0; i<5; i++) {
			Thread t = new Thread(new MyTask((Text) hbox.getChildren().get(i), this));
			t.setDaemon(true);
			t.start();
		}
//		Thread t1 = new Thread(new MyTask((Text) hbox.getChildren().get(0), this));
//		Thread t2 = new Thread(new MyTask((Text) hbox.getChildren().get(1), this));
//		Thread t3 = new Thread(new MyTask((Text) hbox.getChildren().get(2), this));
//		t1.setDaemon(true);
//		t2.setDaemon(true);
//		t3.setDaemon(true);
//		t1.start();
//		t2.start();
//		t3.start();
	}

	public void showText(Text text, boolean show) {
		// the parameter show tells if the text has to appear o disappear
		if (show) {
			text.setVisible(true);
		} else {
			text.setVisible(false);
		}
	}
	class MyTask implements Runnable {
		int timeBudgetms = 1 * 60 * 1000;
		LettersUI instance;
		Text text;

		public MyTask(Text text, LettersUI zeroOROne) {
			this.text = text;
			this.instance = zeroOROne;
		}

		@Override
		public void run() {
			boolean show = true;
			long startTime = System.currentTimeMillis();
			synchronized (instance) {
				while ((startTime + timeBudgetms) > System.currentTimeMillis()) {
					if (!show) {
						instance.showText(text, show);
					}
					while(show && (showing || prev.equals(text.getText()))) {
						try {
//							System.out.println("Thread "+text.getText()+" Waiting");
//							System.out.println("Prev = "+prev+", Show = "+show+", showing = "+showing);
							instance.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					instance.showText(text, show);
					show = !show;
					if (!show) {
						showing = true;
						prev = text.getText();
					} else showing = false;

					if (!showing) {
						instance.notify();
					}
					
//					if (!show) System.out.println("Thread "+text.getText()+" Show");
//					else System.out.println("Thread "+text.getText()+" Hide");
//					System.out.println("Prev = "+prev+", Show = "+show+", showing = "+showing);
					try {
						Random rn = new Random();
						int range = 3000 - 1000 + 1;
						int randomNum = rn.nextInt(range) + 1000;
						Thread.yield();
						Thread.sleep(randomNum);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					showing = false;
				}
			}
		}

//		@Override
//		public void run() {
//			boolean show = true;
//			long startTime = System.currentTimeMillis();
//			synchronized (instance) {
//				while ((startTime + timeBudgetms) > System.currentTimeMillis()) {
//					show = !show;
//					showing = show; 
//					instance.showText(text, show);
//					System.out.println(text.getText()+" "+show+" "+showing+" "+prev);
//					while((show && showing) || prev.equals(text.getText())) {
//						try {
//							System.out.println(text.getText()+" WAIT, Prev = "+prev+", Show = "+show+", showing = "+showing);
//							instance.wait();
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					prev = text.getText();
//					if(show && showing || prev.equals(text.getText())) {
//						System.out.println(text.getText()+" NEXT, Prev = "+prev+", Show = "+show+", showing = "+showing);
//						instance.notify();
//					}
//					System.out.println(text.getText()+" SHOW");
//					try {
//						Random rn = new Random();
//						int range = 3000 - 1000 + 1;
//						int randomNum = rn.nextInt(range) + 1000;
//						Thread.yield();
//						Thread.sleep(randomNum);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
	}
}

