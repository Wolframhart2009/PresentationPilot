package interpreter;

import interpreter.language.toLanguage;

public class Interpreter {

	private toLanguage end;
	
	public Interpreter(toLanguage e){
		this.end = e;
		
		loadLanguage();
	}
	
	public String Interpret(String s){
		
		return null;
	}
	
	private boolean saveDoc(String s){
		
		return false;
	}
	
	public void loadLanguage(){
		System.loadLibrary("wrappers/" + end.getHeader());
	}
	
	
}
