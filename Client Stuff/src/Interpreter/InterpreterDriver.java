package interpreter;

import interpreter.Interpreter;
import interpreter.language.VisualBasic;

public class InterpreterDriver {
	public static void main(String[] args){
		Interpreter n = new Interpreter(new VisualBasic());
		
                try{
                    Thread.sleep(5000);
                    n.Interpret("Forward");
                    
                }catch(Exception w){
                    
                }

	}
}
