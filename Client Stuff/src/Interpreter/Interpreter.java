package interpreter;

import interpreter.language.toLanguage;

public class Interpreter {

	private final toLanguage end;
	
	public Interpreter(toLanguage e){
		this.end = e;
		
		//loadLanguage();
	}
	
	public String Interpret(String s){
		end.Translate(s);
		return null;
	}
	
	private boolean saveDoc(String s){
		
		return false;
	}
	
	private void loadLanguage(){
            System.loadLibrary(end.getHeader());
	}
	
	
}
