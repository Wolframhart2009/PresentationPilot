package interpreter.language;


public interface toLanguage {
    
	/*Input: Received String event from the phone. 
         Output: Returns an error code. Allows for outside handling of errors in the interpreter stage.
          0 - Nothing went wrong
          1 - Script Execution error
          2 - API Issue
          3 - General Issue (Placeholder for more specific error codes)
          This will translate the inputed signal into the language's required format and then execute it 
          (whether by calling an api or by using a script). 
        */
	public int Translate(String s);
        
        /*Input: Nothing
          Output: Path of implemented outside methods or scripts
        */
	public String getHeader();
        
        /*Input: Nothing
          Output: Name of implemented language
        */
        public String toString();
}
