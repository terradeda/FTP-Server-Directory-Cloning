
import java.io.File;
import java.io.IOException;


public class localFileOrganizer {

	private static String prodReportDir;
	private static String FTPDownloadDir;
	
	public localFileOrganizer(){
		prodReportDir = "C:/Users/terradeda/Desktop/Picture Puller for Meter Swap Program/Testing Directory/Neptune Production Reports";
		FTPDownloadDir = "C:/Users/terradeda/Desktop/Picture Puller for Meter Swap Program/Testing Directory/Data";	
	}
	
	
	public static void main(String[] args) {
		
		localFileOrganizer meterSwap = new localFileOrganizer();
		File currentDir = new File(meterSwap.FTPDownloadDir); // current directory
		displayDirectoryContents(currentDir);
	}

	public static void displayDirectoryContents(File dir) {
		
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				
				//If the Current File Object is a directory
				if (file.isDirectory()) {
					System.out.println("Directory:" + file.getCanonicalPath());
					displayDirectoryContents(file);
					
				//The file object is a file.
				} else {
					
					//Print File's Name
					System.out.println("     File Name:" + file.getName());
					
					//If current File Contains the the string "ProdReport" ... This is used to select the production reports so we can move them
					if(file.getName().contains("ProdReport")){
						System.out.println("            Production Report Found!");
						
						
						//Check if file is already at location
						if( new File(prodReportDir + "/" + file.getName()).exists()){
							System.out.println("            -  File Already Exists at Location");
						
						//File not found  at location ... Move file to location specified by prodReportDir
						}
							
						//Print Msg
						System.out.println("            -	Moving File To: Neptune Production Reports");
						file.renameTo(new File(prodReportDir+ "/" + file.getName()));
						
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}