package interpreter;

import interpreter.language.toLanguage;

public class Interpreter {

	private final toLanguage end;
	
	public Interpreter(toLanguage e){
		this.end = e;
	}
	
	public void Interpret(String s){
		int errorCode = end.Translate(s);
                switch(errorCode){
                    case -1:
                        System.exit(0); //Temp until we have solved server memory issues
                        break;
                   case 0:
                       break; //Everything went well
                   case 1:
                       System.out.println("Issue handling outside scripts");
                       break;
                   case 2:
                       System.out.println("Issue using the API");
                       break;
                   case 3:
                       System.out.println("General Issue");
                       break;
                }
	}
	
}
