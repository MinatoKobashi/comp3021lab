package base;

public class TextNote extends Note {

	private String content;
	
	public TextNote(String title){
		super(title);
		this.content = title;
	}
}