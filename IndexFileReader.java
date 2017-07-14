import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class IndexFileReader {
	
	
	
	static HashMap<String, String> productList = new HashMap<String, String>();
	
	static HashMap<String, String> insertionList = new HashMap<String, String>();
	
	static HashMap<String, String> pdList = new HashMap<String, String>();
	
	static HashMap<String, ArrayList > lotList = new HashMap<String, ArrayList>();
	
	static HashMap<String, ArrayList > waferList = new HashMap<String, ArrayList>();
	
	static HashMap<String, String> scribeList = new HashMap<String, String>();
	
	static String filter = "FAB8_";
	
	static String splitLine[];
	
	static String customerFileInfo = "";
    static String productIDFileInfo ="";
    static String lotFileInfo= "";
    static String PDFileInfo = "";
    static String InsertionFileInfo = "";
    static String WaferFileInfo = "";
    static String ScribeFileInfo = "";
    static String DateTimeFileInfo = "";
    
    static ArrayList lotAL = new ArrayList();
    static ArrayList waferAL = new ArrayList();
    
    public IndexFileReader() throws IOException {
    	

    	readFile();
		printProductList();
    }
	
	public static void main (String args[]) throws IOException {
		
	
		//readFile();
		//printProductList();
	
		
	}
		
	public static HashMap printProductList() {
		// TODO Auto-generated method stub
		for (Map.Entry<String, String> entry : productList.entrySet()) {
		    System.out.println("................. + ........ " + entry.getKey() +" : "+entry.getValue());
		    
		}
		
		return productList;
	}
	
	
	
	public static ArrayList printLotList(String productid) {
		// TODO Auto-generated method stub
		//p.1 => l.1, l.2, l.3
		String p = "";
		
		ArrayList l = new ArrayList();
		for (Entry<String, ArrayList> entry : lotList.entrySet()) {
		     
			p = entry.getKey();
		    if ( p.trim().startsWith(productid)) {	
		    	System.out.println("Testonmg");
		    	
		    	l.addAll(entry.getValue());
		    }
		}
		
		return l;
		
	}


	public static void readFile() throws IOException {
		utils u = new utils();
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
			  
			 // System.out.println("***********"  + line);
			  
			  if ( line.contains(filter)) {
				  
				
			      splitLine = line.split("\\|");
			     
			  if ( splitLine[1].trim().startsWith(filter)) {
				  
				u.splitFileInfo(splitLine[1]);
				
				productIDFileInfo = u.getProductIDFileInfo();
			
				lotFileInfo = u.getlotFileInfo();
				
				PDFileInfo = u.getPDFileInfo();
				InsertionFileInfo = u.getInsertionFileInfo();
				WaferFileInfo = u.getWaferFileInfo();
				
				
				
				
				 if ( waferList.containsKey(lotFileInfo)) {
					  waferAL = waferList.get(lotFileInfo);
					  waferList.remove(lotFileInfo);
					  waferAL = new ArrayList();
					  
					  if(!waferAL.contains(lotFileInfo))
						  waferAL.add(lotFileInfo);
					  waferAL.add(lotFileInfo);
					  
					  waferList.put(lotFileInfo, waferAL);
				 }else {
					 //new productid key
					 waferAL = new ArrayList();
					 if(!waferAL.contains(lotFileInfo))
						 waferAL.add(lotFileInfo);
					 waferList.put(lotFileInfo, waferAL);
				 }
				
				
				 
				 if ( lotList.containsKey(productIDFileInfo)) {
					  lotAL = lotList.get(productIDFileInfo);
					  lotList.remove(productIDFileInfo);
					  lotAL = new ArrayList();
					  
					  if(!lotAL.contains(lotFileInfo))
							 lotAL.add(lotFileInfo);
					  lotAL.add(lotFileInfo);
					  
					  lotList.put(productIDFileInfo, lotAL);
				 }else {
					 //new productid key
					 lotAL = new ArrayList();
					 if(!lotAL.contains(lotFileInfo))
						 lotAL.add(lotFileInfo);
					 lotList.put(productIDFileInfo, lotAL);
				 }
				 
				if ( productList.containsKey(productIDFileInfo)) {
					
					productList.remove(productIDFileInfo);
					productList.put(productIDFileInfo, productIDFileInfo);
				
				} else {
					productList.put(productIDFileInfo, productIDFileInfo);
					
				}
				  
			  }
			  
			  }
			  
			  
		   
		  }
		  br.close();
	}
		
	

}
