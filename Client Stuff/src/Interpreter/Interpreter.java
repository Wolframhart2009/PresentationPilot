package interpreter;

import interpreter.language.toLanguage;

public class Interpreter {

	private final toLanguage end;
	
	public Interpreter(toLanguage e){
		this.end = e;
	}
	
	public void Interpret(String s){
		end.Translate(s);
	}
	
}
