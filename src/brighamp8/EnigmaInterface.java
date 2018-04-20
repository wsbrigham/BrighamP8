/*******************************************************************************
*NAME: William Brigham
*EMAIL: wbrigham@cnm.edu
*PROGRAM TITLE: Enigma Encoding & Decoding Machine
*CLASS OBJECTIVE: To act as an interfaces for methods
*******************************************************************************/
package brighamp8;

public interface EnigmaInterface {
//TODO:  This one is not correct.  You need also the key  -2
 public void setCodedMessage(String codedMessage, int key);   
//public void setCodedMessage(String codedMessage);
 //TODO:  There is no setKey method in Enigma.  -2
//public void setKey(int key);
public String getMessage();
public String getCodedMessage();
public void setMessage(String message);
public int getKey();

//TODO:  You are missing one method:  -2
public void setMessage(String message, int key);

//TODO:  These methods are no tincluded// in the interface  -2
//public void decode();
//public void encode();
}
