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
	    String lsource=null;
	    String nsource=null;
	    
	    String ldest = null;
	    String ndest = null;
	
	    
	    if ( source.toString().endsWith("data.dis")) {
	    	
	    	
	    	 lsource = source.toString().replace("data", "limit");
	    	
	       
	         nsource = source.toString().replace(".data.dis", ".nc");
	         nsource = nsource.replace("data", "nc");
	         
	         
	         ldest = dest.toString().replace(".data.dis", ".limit.dis");
	         ndest = dest.toString().replace(".data.dis", ".nc");
	         
	    
	    }
	  
	 
	   
	  
	    //amd_22PLRS11MA.A1_M632W.00_RS-WETILTH-14LPP.01_FWET_01_3D6UA192SAF1_20170220-093352.data.dis
	    
	    System.out.println(" Copying data from "   + "===" + source);
	    System.out.println(" Copying limit from "   + "===" +lsource);
	    System.out.println(" Copying nc from " +   "===" + nsource);
	    
	    
	   
	    
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
	    
	    
	    try {
	        is = new FileInputStream(lsource);
	        os = new FileOutputStream(ldest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	    
	    try {
	        is = new FileInputStream(nsource);
	        os = new FileOutputStream(ndest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    } 
	    
	    System.out.println(" Copyied data to "   + "===" + dest);
	    System.out.println(" Copyied limit to "   + "===" +ldest);
	    System.out.println(" Copyied nc to " +   "===" + ndest);
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

