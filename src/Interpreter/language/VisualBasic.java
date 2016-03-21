package interpreter.language;

public class VisualBasic implements toLanguage{
	
	public native int add(int x, int y);
	
	private String header;
	
	public VisualBasic(){
		this.header = "VB_Pilot";
	}

	@Override
	public String Translate(String s) {
		System.out.println(add(5, 5));
		return null;
	}

	@Override
	public String buildDoc(String s) {
		return null;
	}

	@Override
	public String getHeader() {
		return header;
	}
	

}
