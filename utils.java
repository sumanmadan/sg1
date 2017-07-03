import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class utils {

	private static final Boolean TRUE = null;
	private static final Boolean FALSE = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	protected static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	protected static boolean fileExists (String destPathStr) {
		
		boolean res ;
		File destPath = new File(destPathStr);
		 if (destPath.exists() && destPath.isDirectory()) {
			 
		        System.out.println("Exists");
		        //if the file is present then it will show the msg  
		        res = true;
		        }
		        else{
		        System.out.println("NOT Exists");
		        //if the file is Not present then it will show the msg
		        makeDir(destPath);
		        res = false;
		}
			return res;
	}


	private static void makeDir(File destPath) {
		// TODO Auto-generated method stub
        System.out.println("creating directory: " + destPath.getName());
	    boolean result = false;
        try{
	       	 destPath.mkdir();
	         result = true;
	     } 
	        catch(SecurityException se){
	         //handle it
	        se.printStackTrace();
	    }        
	   if(result) {    
	             System.out.println("DIR created--->" + destPath);  
	   }
		
	}
}
