package interpreter.language;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
                        errorCode = -1;
                        break;   
                }
		return errorCode;
	}

	@Override
	public String getHeader() {
		return VisualBasic.header;
	}
        
        @Override
        public String getCurNotes(){
            StringBuilder s = new StringBuilder();
            
            try{
                Process proc = Runtime.getRuntime().exec("wscript " + header + "gettitleandnotes.vbs");
//                BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//                String c;
//                while((c = br.readLine()) != null){
//                    s.append(c);
//                }
//                
//                System.out.println(s.toString());
//                return s.toString();
                return null;
            }
            catch(Exception e){
                return null;
            }
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
        
        
        @Override
        public String toString(){
            return "Visual Basic";
        }

}
