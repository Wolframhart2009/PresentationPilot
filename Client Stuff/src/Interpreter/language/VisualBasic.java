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
                        errorCode = exit();
                        break;   
                }
		return errorCode;
	}

	@Override
	public String getHeader() {
		return VisualBasic.header;
	}
        
        @Override
        public String[] getCurNotes(){
            String results[] = new String[2];
            StringBuilder title = new StringBuilder();
            StringBuilder notes = new StringBuilder();
            Scanner noteReader = null;
            File tempFile = null;
            
            try{
                Process proc = Runtime.getRuntime().exec("wscript " + header + "gettitleandnotes.vbs");
                
                proc.waitFor(); //Wait for the notes to be read
                tempFile = new File(header + "currentnotes.txt");

                if(tempFile.exists()){
                    noteReader = new Scanner(tempFile);

                    if(noteReader.hasNextLine()){
                       title.append(noteReader.nextLine());
                    }

                    while(noteReader.hasNextLine()){
                       notes.append(noteReader.nextLine());
                    }

                    noteReader.close();
                    tempFile.delete();

                    if(title.toString().equals("") || title.toString() == null){ 
                        title = new StringBuilder(" ");
                    }

                    if(notes.toString().equals("") || notes.toString() == null){ 
                        notes = new StringBuilder(" ");
                    }

                    results[0] = title.toString();
                    results[1] = notes.toString();

                    return results;
                }
                else{
                    return null;
                }
            }
            catch(Exception e){
                System.out.println(e);
                System.out.println(tempFile.exists());
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
