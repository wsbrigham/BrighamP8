/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brighamp8;

/**
 *
 * @author WSBRI
 */
public class TexasEnigma extends Enigma{
    private String localCodedMessage;
    private int localKey = 1;
    
    
    public TexasEnigma(){
        
        super();
        
        localCodedMessage = "";
        localKey = 1;
    }
    
    private void encode(){
        
        
    }
    
    private void decode(){
        
    }

    @Override
     public void setMessage(String message){
         
         super.setMessage(message);
         localCodedMessage = super.getCodedMessage();
         localKey = super.getKey();
         encode();
         
     }
     
     @Override
    public void setMessage(String message, int key){
        super.setMessage(message, key);
         localCodedMessage = super.getCodedMessage();
         localKey = super.getKey();
         encode();
         localCodedMessage = message;
         localKey = key;
         decode();
         super.setCodedMessage(localCodedMessage, localKey);
         
        
    }
     
    @Override
    public void setCodedMessage(String codedMessage, int key){
        
    } 
    
    @Override 
    public String getCodedMessage(){
        
        return localCodedMessage;
        
    }
    
    @Override
     public int getKey(){
         
         return localKey;
     }
    

      
    
     
    
    
}
