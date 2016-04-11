package interpreter.language;

public class VisualBasic implements toLanguage{
		
	private static final String header = "./languages/VisualBasic/";
	
	public VisualBasic(){
            
	}

	@Override
	public int Translate(String s) {
                int errorCode = 0;
            
                switch(s){
                    case "first":
                        errorCode = runSlide();
                        break;
                    case "next":
                        errorCode = forwardSlide();
                        break;
                    case "back":
                        errorCode = backwardSlide();
                        break;
                    case "quit":
                        exit(); //Temp until we are done fixing server memory issue
                        errorCode = -1;
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
        
        public int exit(){
            int errorCode = 0;
            try{ 
                Runtime.getRuntime().exec("wscript " + header + "quit.vbs");
                errorCode = 0;
            }
            catch(Exception e){
                System.out.println("Exception: " + e);
                errorCode = 1;
            }
            return errorCode;
        }
	
        public int runSlide(){
            int errorCode = 0;
            try{ 
                Runtime.getRuntime().exec("wscript " + header + "runslide.vbs");
                errorCode = 0;
            }
            catch(Exception e){
                System.out.println("Exception: " + e);
                errorCode = 1;
            }
            return errorCode;
        }

}
