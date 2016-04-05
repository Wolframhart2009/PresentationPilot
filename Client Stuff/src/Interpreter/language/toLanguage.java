package interpreter.language;


public interface toLanguage {
	
	public int Translate(String s);
        /*Returns an error code
          0 - Nothing went wrong
          1 - Script Execution error
        */
        
	public String getHeader();
        public String toString();
}
