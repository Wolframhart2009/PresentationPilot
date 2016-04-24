package interpreter.language;

import java.io.File;
import java.util.Scanner;

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
            Scanner noteReader;
            
            try{
                Runtime.getRuntime().exec("wscript " + header + "gettitleandnotes.vbs");
                File tempFile = new File(header + "currentnotes.txt");

                noteReader = new Scanner(tempFile);
                
                while(noteReader.hasNextLine()){
                   s.append(noteReader.nextLine());
                }
                
                noteReader.close();
                tempFile.delete();
                
                
                return s.toString();
            }
            catch(Exception e){
                System.out.println("Error!");
                return null;
            }
        }
        
        public int forwardSlide(){
            int errorCode;
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
            int errorCode;
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
            int errorCode;
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
            int errorCode;
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
