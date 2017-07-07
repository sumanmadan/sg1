import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;


public class IndexFileReader {
	
	
	
	HashMap<String, String> productList = new HashMap<String, String>();
	
	HashMap<String, String> insertionList = new HashMap<String, String>();
	
	HashMap<String, String> pdList = new HashMap<String, String>();
	
	HashMap<String, String> lotList = new HashMap<String, String>();
	
	HashMap<String, String> waferList = new HashMap<String, String>();
	
	HashMap<String, String> scribeList = new HashMap<String, String>();
	
	static String filter = "FAB8_";
	
	static String splitLine[];
	
	public static void main (String args[]) throws IOException {
		
		readFile();
	}
		
	public static void readFile() throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("\\\\fc8fstp01\\wet_public\\INDEX\\2017\\19.index"));
		/*
		amd/ZEPPLN14MJ.H0/M73N5.00/LV-WETILTH-14LPP.01/FWET                | 
		FAB8_amd_ZEPPLN14MJ.H0_M73N5.00_LV-WETILTH-14LPP.01_FWET_13_BBASW008MVB2_20170514-233702            | 
		FAB8_amd_ZEPPLN14MJ.H0_M73N5.00_LV-WETILTH-14LPP.01_FWET_20170514-163249_13_BBASW008MVB2_20170514-233702_20170514-235939              | 
		ad3:data:limit:nc:vmap
		
	
		*/
		
		  String line;
		  int i = 0;

		  while ((line = br.readLine()) != null) {
			  
			  System.out.println("***********"  + line);
			  
			  if ( line.contains(filter)) {
			  splitLine = line.split("|");
			  
			  if ( splitLine[1].startsWith(filter)) {
				  
			  }
			  
			  }
			  
			  
		   
		  }
		  br.close();
	}
		
	

}
