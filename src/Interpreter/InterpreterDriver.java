package interpreter;

import interpreter.language.VisualBasic;

public class InterpreterDriver {
	public static void main(String[] args){
		Interpreter n = new Interpreter(new VisualBasic());
		
		n.Interpret(null);
	}
}
