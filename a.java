import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class a extends utils{
  public static void main(String[] args) {
    JFileChooser fileopen = new JFileChooser();
    FileFilter filter = new FileNameExtensionFilter("dis files", "dis");
    fileopen.addChoosableFileFilter(filter);

    int ret = fileopen.showDialog(null, "Open file");

    if (ret == JFileChooser.APPROVE_OPTION) {
      File file = fileopen.getSelectedFile();
      String path = fileopen.getSelectedFile().getAbsolutePath();
      String name = fileopen.getSelectedFile().getName();
      
      System.out.println(file);
      System.out.println(path);
      
      String dest = System.getProperty("user.home");
      File destPath = new File(dest +"/Downloads/" + name ); 
      
      System.out.println("Copied to :" + destPath);
      
      long start = System.nanoTime();
      //copyFileUsingStream(file, destPath);
      
      //System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));
    }
  }
}