import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 

import org.apache.commons.net.ftp.FTPClient;
 
public class FTPDirectoryCloning {
	
	private String server;
	private int port;
	private String user;
	private String pass;
	
	public FTPDirectoryCloning(){
		/*
		 * Constructor Function
		 * -Initializes the server Log In Variables From a specific File 
		 */
		
		try{
	    	   //Create a file Reader Object
	    	   FileReader inputFile = new FileReader("H:/git/FTP DownloadScript/ACTUAL - FTPServerLoginDetails.txt");
	    	   
	    	   //Create a buffered reader to Read from the file
	    	   BufferedReader bufferedReader = new BufferedReader(inputFile);
	    	   
	    	   //Define A Variable to hold the current line of data
	    	   String line;
	    	   //Define a line counter and initialize it to 0
	    	   int lineCounter =0;
	    	   
	    	   while((line = bufferedReader.readLine()) != null){
	    		   
	    		   
	    		   //Initialize each of the private variables used to log into the FTP server
	    		   switch(lineCounter){
	    		   		case 0: server = line;
	    		   				break;
	    		   		case 1: port = Integer.parseInt(line); 
	    		   				break;
	    		   		case 2: user = line;
	    		   				break;
	    		   		case 3: pass = line;
	    		   				break;
	    		   		default: server = null;
	    		   }
	    	   
	    	   	   //Increment the line counter
	    		   lineCounter++;
	    	   }
	    	   
	    	   //Close the buffered Reader
	    	   bufferedReader.close();
	    	   
	       }catch(Exception e){
	    	   System.out.println("Error While Reading Server Log In File:" + e.getMessage());
	       }
	        
	}
 
    public static void main(String[] args) {
    	
    	//Define an FTP Directory Cloning object to collect the FTP server Log In Data  
        FTPDirectoryCloning meterSwap = new FTPDirectoryCloning();
        
        FTPClient ftpClient = new FTPClient();
 
        try {
            // connect and login to the server
            ftpClient.connect(meterSwap.server,meterSwap.port);
            ftpClient.login(meterSwap.user,meterSwap.pass);
 
            // use local passive mode to pass firewall
            ftpClient.enterLocalPassiveMode();
 
            System.out.println("Connected");
 
            String remoteDirPath = "/";
            String saveDirPath = "C:/Users/terradeda/Desktop/Picture Puller for Meter Swap Program/Testing Directory/Data";
 
            FTPUtil.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath);
 
            // log out and disconnect from the server
            ftpClient.logout();
            ftpClient.disconnect();
 
            System.out.println("Disconnected");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
}