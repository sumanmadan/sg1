import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class utils {

	private static final Boolean TRUE = null;
	private static final Boolean FALSE = null;
	
	private static String lsource = null;
	private static String nsource = null;
	private static String dsource = null;
	private static String jcsource = null;
	
	private static String ndest = null;
	private static String ldest = null;
	private static String ddest = null;
	private static String jcdest = null;
	
	private static String userHome = null;
	private static String destPathStr = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	public  utils () {
		 
		 
		
		
	}
	
	

	

	
	
	
    public static String getUserHome() {
    	 
    	userHome = System.getProperty("user.home");
		return userHome;
		
   }
    
    public static String getDestPathStr() {
		
    	destPathStr = userHome + File.separator + "Downloads" + File.separator + "JCALGUI" + File.separator; 
 		return destPathStr;
 		
    }
	
	
	public static String getDSource() {
		
		return dsource;
		
	}
	
	
   public static String getLSource() {
		
		return lsource;
		
	}
   
   public static String getNSource() {
		
		return nsource;
		
	}
   
   public static String getDDest() {
		
		return ddest;
		
	}
	
	
  public static String getLDest() {
		
		return ldest;
		
	}
  
  public static String getNDest() {
		
		return ndest;
		
  }
  
   
   
  public static String getJCSource() {
		
		return jcsource;
		
	}

public static String getJCDest() {
		
		return jcdest;
		
}

 
   
	
	protected static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	   
	
	    
	    if ( source.toString().endsWith("data.dis")) {
	    	dsource = source.toString();
	    	lsource = source.toString().replace("data", "limit");
	    	nsource = source.toString().replace(".data.dis", ".nc");
	        nsource = nsource.replace("data", "nc");
	        ldest = dest.toString().replace(".data.dis", ".limit.dis");
	        ndest = dest.toString().replace(".data.dis", ".nc");
	        ddest = dest.toString();
	    
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



	public static void copyConfigFileUsingStream(File source, File dest) throws IOException {
		// TODO Auto-generated method stub
		 InputStream is = null;
		 OutputStream os = null;
		   
		  //JCal Config Copying
		    
		 System.out.println(" Copying data from "   + "===" + source);
		
		  jcsource = source.toString();
		   
		    
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
		    
		    
		    jcdest = dest.toString();
		    
		    System.out.println(" Copyied data to "   + "===" + dest);
		   
		
		
		
		
	}


	public static void prepJConfigLocal(File source) {
		// TODO Auto-generated method stub
		
		   jcdest = source.toString();
		  
		
	}
	
	
	

	public static void prepFileLocal(File source) {
		// TODO Auto-generated method stub
		
		   String dest = source.toString();
		   if ( source.toString().endsWith("data.dis")) {
		    	dsource = source.toString();
		    	lsource = source.toString().replace("data", "limit");
		    	nsource = source.toString().replace(".data.dis", ".nc");
		        nsource = nsource.replace("data", "nc");
		        ldest = dest.toString().replace(".data.dis", ".limit.dis");
		        ndest = dest.toString().replace(".data.dis", ".nc");
		        ddest = dest.toString();
		    
		    }
		
	}
}



