
 
import java.io.*;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
 
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooserDemo extends JPanel implements ActionListener {
	
	
    static private final String newline = "\n";
    JButton openButton, choseLocalButton,openJCButton, choseLocalJCButton, JCalparaSOS;
    JTextArea log;
    JFileChooser fc;
    JFileChooser fileopen;
    FileFilter filter;
    
    utils U = new utils();
    
    
    File source;
    File dest; 
    
    String path ;
    String name ;
    String userHome;
    String destPathStr ; 
    
    String lArg;
    String dArg;
    String ncArg;
    String JConfigArg;
    
    boolean chk;
    int ret;
    
    String[] fileInfo;
    String[] folderInfo;
    
    
     
   
    
    public FileChooserDemo() {
    	
        super(new BorderLayout());
        
        userHome = utils.getUserHome();
        destPathStr = utils.getDestPathStr();
        
     
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
        //Create a file chooser
        fc = new JFileChooser();
 
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Fetch Public Files...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);
 
        
        openJCButton = new JButton("Fetch JCal Config...", createImageIcon("images/Save16.gif"));
        openJCButton.setEnabled(false);
        openJCButton.addActionListener(this);
        
        
        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        choseLocalButton = new JButton("Load Local Files...", createImageIcon("images/Save16.gif"));
        choseLocalButton.addActionListener(this);
        
        
        choseLocalJCButton = new JButton("Load Local JCal Config...", createImageIcon("images/Save16.gif"));
        choseLocalJCButton.addActionListener(this);
        
 
        JCalparaSOS =  new JButton("Sprint JCal...", createImageIcon("images/Save16.gif"));
        JCalparaSOS.addActionListener(this);
        
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
       /* buttonPanel.add(openButton);
        buttonPanel.add(openJCButton);
        
        buttonPanel.add(choseLocalButton);
        buttonPanel.add(choseLocalJCButton);*/
        
        JSeparator js1 = new JSeparator();
        JSeparator js2 = new JSeparator();
        
        JLabel jl1 = new JLabel("Chose your dis from WET Public "  );
        jl1.setForeground(Color.BLUE);
        JLabel jl2 = new JLabel("Chose your dis from Local " + destPathStr);
        jl2.setForeground(Color.BLUE);
        JLabel jl3 = new JLabel("get set go JCalpara");
        jl3.setForeground(Color.BLUE);
        
        Box box = Box.createVerticalBox();
        
        box.add(Box.createVerticalStrut(10));  
        box.add(Box.createHorizontalStrut(10));
        
        box.add(jl1);
        
        box.add(openButton);
        box.add(Box.createVerticalStrut(10));  
        box.add(openJCButton);
        box.add(Box.createVerticalStrut(10));  
        box.add(js1);
        box.add(Box.createHorizontalStrut(10));
        
        box.add(jl2);
        box.add(choseLocalButton);
        box.add(Box.createVerticalStrut(10));  
        box.add(choseLocalJCButton);
        box.add(Box.createVerticalStrut(10));  
        
        
        box.add(js2);
        box.add(Box.createHorizontalStrut(10));
        box.add(jl3);
        box.add(JCalparaSOS);
        
        
 
        //Add the buttons and the log to this panel.
        //add(buttonPanel, BorderLayout.PAGE_START);
        add(box, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
        box.add(Box.createVerticalStrut(10));  
        
       
        
    }
 
  

	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource() == JCalparaSOS) {
			
			dArg = utils.getDDest();
	        lArg = utils.getLDest();
	        ncArg = utils.getNDest();
	        JConfigArg = utils.getJCDest();
	        
	        if ( dArg ==null || lArg ==null || ncArg ==null || JConfigArg ==null ) {
	        	
	        	log.append("You forgot to chose your data files "   + newline );
	        } else {
 
	        log.append("Run Variables Under Construction: "   + newline );
	        log.append("Data dis arguement .... " +  dArg + newline);
	        log.append("Limit dis arguement .... " + lArg + newline);
	        log.append("NC arguement .... " + ncArg + newline);
	        log.append("Config File arguement .... : "   + JConfigArg + newline );
	        log.append("Prepared to run Jcalpara.... "  + newline );
	        
	        }
			
		}
		
		
		if (e.getSource() == choseLocalJCButton) {
			
			
			 JFileChooser fileopen = new JFileChooser();
	            int ret = fileopen.showDialog(null, "Open file");

	            if (ret == JFileChooser.APPROVE_OPTION) {
	               source = fileopen.getSelectedFile();
	               path = fileopen.getSelectedFile().getAbsolutePath();
	               name = fileopen.getSelectedFile().getName();
	              
	               long start = System.nanoTime();
	               
	               try {
	                   
	            	   
	            	 dArg = utils.getDDest();
				     lArg = utils.getLDest();
				     ncArg = utils.getNDest();
				     utils.prepJConfigLocal(source);
	   			     JConfigArg = utils.getJCDest();  
		 
			        log.append("Run Variables Under Construction: "   + newline );
			        log.append("Data dis arguement .... " +  dArg + newline);
			        log.append("Limit dis arguement .... " + lArg + newline);
			        log.append("NC arguement .... " + ncArg + newline);
			        log.append("Config File arguement : "   + JConfigArg + newline );
   				
	   				
	               }
	               catch (Exception e1) {
		   				// TODO Auto-generated catch block
		   				e1.printStackTrace();
	               }
			
	            }
		}
		
		 if (e.getSource() == openJCButton) {
			 
			 
			
			    JFileChooser fileopen = new JFileChooser();
	            int ret = fileopen.showDialog(null, "Open file");

	            if (ret == JFileChooser.APPROVE_OPTION) {
	               source = fileopen.getSelectedFile();
	               path = fileopen.getSelectedFile().getAbsolutePath();
	               name = fileopen.getSelectedFile().getName();
	              
	               long start = System.nanoTime();
	               
	               try {
	                   
	                  	  
	   				utils.copyConfigFileUsingStream(source, dest);
	   				
	   				
	   				
	   			      dArg = utils.getDDest();
				      lArg = utils.getLDest();
				      ncArg = utils.getNDest();
				      JConfigArg = utils.getJCDest();
			 
				      log.append("Run Variables Under Construction: "   + newline );
				      log.append("Data dis arguement .... " +  dArg + newline);
				      log.append("Limit dis arguement .... " + lArg + newline);
				      log.append("NC arguement .... " + ncArg + newline);
				      log.append("Config File arguement : "   + JConfigArg + newline );
	   				
	   				
	   				//Copy 
	   			} catch (Exception e1) {
	   				// TODO Auto-generated catch block
	   				e1.printStackTrace();
	   			}
			
	           }
			 
		 }
 
        //Handle open button action.
        if (e.getSource() == openButton) {
        	
        	openJCButton.setEnabled(true);
        	fileopen = new JFileChooser();
            filter = new FileNameExtensionFilter("dis files", "dis");
            //fileopen.addChoosableFileFilter(filter);
            fileopen.setFileFilter(filter);
            ret = fileopen.showDialog(null, "Open file");

            if (ret == JFileChooser.APPROVE_OPTION) {
               source = fileopen.getSelectedFile();
               path = fileopen.getSelectedFile().getAbsolutePath();
               name = fileopen.getSelectedFile().getName();
               //String pattern = Pattern.quote(System.getProperty("file.separator"));
               String pattern = "_";  
               fileInfo = source.toString().split(pattern);
               System.out.println(source);
               System.out.println(path);
               
               pattern = Pattern.quote(System.getProperty("file.separator"));
               folderInfo = source.toString().split(pattern);
               
               //FAB8_amd_22PLRS11MA.A1_M632W.00_RS-WETILTH-14LPP.01_FWET_01_3D6UA192SAF1_20170220-093352.data.dis
               log.append("Customer : "   + fileInfo[1]+ newline );
               log.append("ProductID : "   + fileInfo[2]+ newline );
               log.append("Lot : "   + fileInfo[3]+ newline );
               log.append("PD : "   + fileInfo[4]+ newline );
               log.append("Insertion : "   + fileInfo[5]+ newline );
               log.append("Wafer : "   + fileInfo[6]+ newline );
               log.append("Scribe : "   + fileInfo[7]+ newline );
               log.append("DateTime : "   + fileInfo[8]+ newline );
               //userHome = System.getProperty("user.home");
               //destPathStr = userHome + File.separator + "Downloads" + File.separator + "JCALGUI" +  File.separator; 
               dest = new File(destPathStr + name); 
               chk = utils.fileExists(destPathStr);
               log.append("Folder: "   + destPathStr + " Exists =  "  + chk + newline );
               long start = System.nanoTime();
 
              try {
            
                  	  
				utils.copyFileUsingStream(source, dest);
				
				
				//Copy 
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  log.append(source + " Copied to " + "Folder: "   + destPathStr   + newline );
              
              //System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));
            }
            	
            	
            
            	
           
          
        //Handle save button action.
        } else if (e.getSource() == choseLocalButton) {
        	
        	openJCButton.setEnabled(true);
        	fileopen = new JFileChooser();
            filter = new FileNameExtensionFilter("dis files", "dis");
            //fileopen.addChoosableFileFilter(filter);
            fileopen.setFileFilter(filter);
            ret = fileopen.showDialog(null, "Open file");

            if (ret == JFileChooser.APPROVE_OPTION) {
               source = fileopen.getSelectedFile();
               path = fileopen.getSelectedFile().getAbsolutePath();
               name = fileopen.getSelectedFile().getName();
               //String pattern = Pattern.quote(System.getProperty("file.separator"));
               String pattern = "_";  
               fileInfo = source.toString().split(pattern);
               System.out.println(source);
               System.out.println(path);
               
               pattern = Pattern.quote(System.getProperty("file.separator"));
               folderInfo = source.toString().split(pattern);
               
               //FAB8_amd_22PLRS11MA.A1_M632W.00_RS-WETILTH-14LPP.01_FWET_01_3D6UA192SAF1_20170220-093352.data.dis
               log.append("Customer : "   + fileInfo[1]+ newline );
               log.append("ProductID : "   + fileInfo[2]+ newline );
               log.append("Lot : "   + fileInfo[3]+ newline );
               log.append("PD : "   + fileInfo[4]+ newline );
               log.append("Insertion : "   + fileInfo[5]+ newline );
               log.append("Wafer : "   + fileInfo[6]+ newline );
               log.append("Scribe : "   + fileInfo[7]+ newline );
               log.append("DateTime : "   + fileInfo[8]+ newline );
               //userHome = System.getProperty("user.home");
               //destPathStr = userHome + File.separator + "Downloads" + File.separator + "JCALGUI" +  File.separator; 
              
             
             
               long start = System.nanoTime();
 
              try {
            
                  	  
				utils.prepFileLocal(source);
				
				
				//Copy 
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  log.append(source + " Copied to " + "Folder: "   + destPathStr   + newline );
        	
        	
            }
        	
        }
       
            
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JCALGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new FileChooserDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
