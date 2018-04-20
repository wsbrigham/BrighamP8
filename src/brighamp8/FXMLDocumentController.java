/*******************************************************************************
*NAME: William Brigham
*EMAIL: wbrigham@cnm.edu
*PROGRAM TITLE: Enigma Encoding & Decoding Machine
*CLASS OBJECTIVE: To drive the user interface
*******************************************************************************/
package brighamp8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField txtKey;           //for the encryption key field
    @FXML private TextField txtMessage;       //for the decoded message field
    @FXML private TextField txtCodedMessage;  //for the coded message field
    @FXML private RadioButton rb2;            //for the enter a key radio dial
    @FXML private RadioButton rb1;
    @FXML private TextField txtEnigmaKey;     //for the encryption key
    @FXML private Button btnDecode;           //for the decode button
    private int key;
    private String message;
    private String codedMessage;
    private Enigma enigma = new Enigma();
    

    /*
    *event handler for File>Open
    */
    @FXML
     private void handleMenuOpenAction(ActionEvent event){

        txtEnigmaKey.setText("");
        txtMessage.setText("");
        txtCodedMessage.setText("");
        txtKey.setText("");
        rb1.setSelected(true);
        
        /*
        *create new file chooser
        */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
        
        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);  
        
        if(file != null)
        {
            try {
                
                String filename = file.getCanonicalPath(); 
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile); 
               codedMessage = inputFile.nextLine();
               key = inputFile.nextInt();                          
               txtCodedMessage.setText(codedMessage);               
               txtKey.setText(key + "");

               inputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
     /*
     *event handler for random, automatic encryption key creation
     */
     @FXML
    private void handleGeneratedKeyAction(ActionEvent event){
            
    }
    
    /*
    *event handler for manually entering an encryption key
    */
    @FXML
    public void handleEnterKeyAction(ActionEvent event){
        txtEnigmaKey.requestFocus();
    }
    
    /*
    *event handler for the Decode button
    */
    @FXML
    private void handleDecodeAction(ActionEvent event)throws IOException{
        
        enigma.setCodedMessage(codedMessage, key);          
        txtKey.setText(String.valueOf(key));
        txtMessage.setText(enigma.getMessage()); 
        txtCodedMessage.setText(codedMessage);

    }
    
    /*
    *event handler for the Encode button
    */
    @FXML
    private void handleEncodeAction(ActionEvent event){

        boolean valuesEntered = true;
        message = txtMessage.getText();
        
        if(rb2.isSelected()){
            
          try{ 

              key = Integer.parseInt(txtEnigmaKey.getText());
              //TODO:  Now call
              enigma.setMessage(message, key);
             
          }
           catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error\n\nYou Didn't input a "
                + "value into the Enter a Key field.\n\n",
                "Enigma Encryption Machine", JOptionPane.ERROR_MESSAGE);
            
            valuesEntered = false;
           }
        }
        else{  // Enigma will create a random key
           enigma.setMessage(message);
            
        }
        
        /*
        *if there's nothing in the message field, display an error
        */
        if(txtMessage.getText().equalsIgnoreCase("")){

            JOptionPane.showMessageDialog(null, "Error\n\nYou Didn't input a "
                + "message in the message field.\n\n",
                "Enigma Encryption Machine", JOptionPane.ERROR_MESSAGE);
            
            valuesEntered = false;
        }
        
        if(valuesEntered == true){

            txtKey.setText(String.valueOf(enigma.getKey()));
            txtMessage.setText(message);            
            txtCodedMessage.setText(enigma.getCodedMessage());
        }
    }
    
    /*
    *event handler for the Clear button which clears all the data in the form
    */
    @FXML
    private void handleClearAction(ActionEvent event){
        txtEnigmaKey.setText("");
        txtMessage.setText("");
        txtCodedMessage.setText("");
        txtKey.setText("");
        rb1.setSelected(true);
    }   
    
    /*
    *event handler for  About Enigma>About which displays a panel with 
    *version information
    */
    @FXML
    private void handleMenuAboutAction(ActionEvent event){
          JOptionPane.showMessageDialog(null,"Enigma Encoding & "
            + "Decoding Machine v1.8", "Enigma Encryption Macnine", 
            JOptionPane.PLAIN_MESSAGE);
    }
    
    /*
    *event handler for File>Save File menu item
    */
    @FXML
    private void handleMenuSave(ActionEvent event){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save Coded Message to a File");
        
        /*
        *Set extension filter
        */
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);  
        
        /*
        *Show the Save File Dialog
        */
        File file = fileChooser.showSaveDialog(null); 
        
        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(myFile);
                outputFile.println(enigma.getCodedMessage());
                outputFile.println(enigma.getKey());                             
                outputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        
        txtEnigmaKey.setText("");
        txtMessage.setText("");
        txtCodedMessage.setText("");
        txtKey.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
}
