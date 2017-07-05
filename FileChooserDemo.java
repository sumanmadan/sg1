
 
 
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
    JButton openButton, saveButton, runVariableButton;
    JTextArea log;
    JFileChooser fc;
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
    
    String[] fileInfo;
    String[] folderInfo;
    
    
     
    private void setters() {
  		// TODO Auto-generated method stub
    	
    	userHome = System.getProperty("user.home");
    	destPathStr = userHome + File.separator + "Downloads" + File.separator + "JCALGUI" + File.separator; 
  	}
    
    public FileChooserDemo() {
    	
        super(new BorderLayout());
        setters(); 
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
        openButton = new JButton("Open a File...", createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);
 
        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...", createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);
        
        
        runVariableButton = new JButton("set the variables...", createImageIcon("images/Save16.gif"));
        runVariableButton.setEnabled(false);
        runVariableButton.addActionListener(this);
 
 
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(runVariableButton);
 
        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
 
  

	public void actionPerformed(ActionEvent e) {
		
		
		 if (e.getSource() == runVariableButton) {
			 
			 log.append("Run Variables Under Construction: "   + newline );
			 log.append("Data dis arguement .... " +  utils.getDSource());
			 log.append("Limit dis arguement .... " + utils.getLSource());
			 log.append("NC  arguement .... " + utils.getNSource());
		 }
 
        //Handle open button action.
        if (e.getSource() == openButton) {
        	
        	runVariableButton.setEnabled(true);
        	JFileChooser fileopen = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("dis files", "dis");
            //fileopen.addChoosableFileFilter(filter);
            fileopen.setFileFilter(filter);
            int ret = fileopen.showDialog(null, "Open file");

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
				utils.runFileNames(source.toString());
				
				//Copy 
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  log.append(source + " Copied to " + "Folder: "   + destPathStr   + newline );
              
              //System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));
            }
            	
            	
            
            	
           
          
        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileChooserDemo.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
                log.append("Saving: " + file.getName() + "." + newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
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
