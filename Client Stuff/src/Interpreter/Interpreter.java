package interpreter;

import interpreter.language.toLanguage;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Interpreter {

	private final toLanguage end;
        private final StringBuilder errorLog;
	
	public Interpreter(toLanguage e){
		this.end = e;
                this.errorLog = new StringBuilder();
                errorLog.append("Interpreter initialized with language: ").append(e.toString()).append("\n");
	}
	
	public void Interpret(String s){
                errorLog.append("Event Triggered: ").append(s).append("\n");
                System.out.println("Translation requested on: " + s);
		int errorCode = end.Translate(s);
                switch(errorCode){
                    case -1:
                        errorLog.append("Server Exited").append("\n");
                        break;
                   case 0:
                       break; //Everything went well
                   case 1:
                       errorLog.append("Issue handling script in location: ").append(end.getHeader()).append("\n");
                       break;
                   case 2:
                       errorLog.append("Issue using connected API").append("\n");
                       break;
                   case 3:
                       errorLog.append("General Issue occured").append("\n");
                       break;
                }
	}
        
        public String getCurrentNotes(){
            String titleNote = end.getCurNotes();
            return(titleNote);
        }
        
        public void releaseErrorLog(){
            GregorianCalendar calender = new GregorianCalendar();
            StringBuilder fileFormat = new StringBuilder();
            
            fileFormat.append(calender.get(Calendar.MONTH));
            fileFormat.append(calender.get(Calendar.DAY_OF_MONTH));
            fileFormat.append(calender.get(Calendar.YEAR));
            fileFormat.append(calender.get(Calendar.HOUR));
            fileFormat.append(calender.get(Calendar.MINUTE));
            fileFormat.append(calender.get(Calendar.SECOND));
            fileFormat.append(".log");

            try{
              PrintWriter fileWriter = new PrintWriter(fileFormat.toString());
              fileWriter.print(errorLog.toString());
              fileWriter.close();
            }
            catch(Exception e){
                System.out.println("error creating file");
            }
        }
	
}
