package interpreter.language;

public class VisualBasic implements toLanguage{
		
	private static final String header = "languages/VisualBasic/";
	
	public VisualBasic(){
            
	}

	@Override
	public int Translate(String s) {
                int errorCode = 0;
            
                switch(s){
                    case "Forward":
                        errorCode = forwardSlide();
                        break;
                    case "Backward":
                        errorCode = backwardSlide();
                        break;
                }
		return errorCode;
	}

	@Override
	public String getHeader() {
		return VisualBasic.header;
	}
        
        public int forwardSlide(){
            int errorCode = 0;
            try{ 
                Runtime.getRuntime().exec("wscript " + header + "forward.vbs");
                errorCode = 0;
            }
            catch(Exception e){
                System.out.println("Exception: " + e);
                errorCode = 1;
            }
            return errorCode;
        }
        
        public int backwardSlide(){
            int errorCode = 0;
            try{ 
                Runtime.getRuntime().exec("wscript " + header + "backward.vbs");
                errorCode = 0;
            }
            catch(Exception e){
                System.out.println("Exception: " + e);
                errorCode = 1;
            }
            return errorCode;
        }
	

}
