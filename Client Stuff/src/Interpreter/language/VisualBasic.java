package interpreter.language;

public class VisualBasic implements toLanguage{
		
	private static final String header = "libVB_Pilot";
	
	public VisualBasic(){
            
	}

	@Override
	public String Translate(String s) {
		
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
        
        public void forwardSlide(){
            Runtime.getRuntime().exec();
        }
	

}
