package interpreter.language;

public class VisualBasic implements toLanguage{
	
	public native int add(int x, int y);
	
	private static final String header = "libVB_Pilot";
	
	public VisualBasic(){
            
	}

	@Override
	public String Translate(String s) {
		add(5, 5);
		return null;
	}

	@Override
	public String buildDoc(String s) {
		return null;
	}

	@Override
	public String getHeader() {
		return VisualBasic.header;
	}
	

}
