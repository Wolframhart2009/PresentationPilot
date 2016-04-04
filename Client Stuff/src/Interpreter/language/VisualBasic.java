package interpreter.language;

public class VisualBasic implements toLanguage{
		
	private static final String header = "libVB_Pilot";
	
	public VisualBasic(){
            
	}

	@Override
	public String Translate(String s) {
		if(s.equals("Forward")){
                    forwardSlide();
                }
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
            try{
//                String script = "forward.vbs";
//                String executable = "C:\\windows\\...\\vbs.exe";
//                String cmdArr[] = {executable, script};
                
                Runtime.getRuntime().exec("wscript forward.vbs");
            }
            catch(Exception e){
                System.out.println("Exception: " + e);
            }
        }
	

}
