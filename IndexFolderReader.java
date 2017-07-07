
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class IndexFolderReader {
    public static void main( String[] args ) throws IOException {
      int maxFiles = 10;
      System.out.println( "TEST BIG DIR" );
      nioRun( "\\\\fc8fstp01\\wet_public\\INDEX\\2017", maxFiles );
      ioRun( "\\\\fc8fstp01\\wet_public\\INDEX\\2017", maxFiles );
    }

   // the classical way
    private static void ioRun( String filePath, int maxFiles )
      throws IOException {
      int i = 1;
      System.out.println( "IO run" );
      long start = System.currentTimeMillis();
      File folder = new File( filePath );
      File[] listOfFiles = folder.listFiles();
      // System.out.println("Total : " + listOfFiles.length);
      for (File file : listOfFiles) {
        System.out.println( "" + i + ": " + file.getName() );
        if (++i > maxFiles) break;
      }
      long stop = System.currentTimeMillis();
      System.out.println( "Elapsed: " + (stop - start) + " ms" );
    }

   // the new way
    private static void nioRun( String filePath, int maxFiles )
       throws IOException {
      int i = 1;
      System.out.println( "NIO run" );
      long start = System.currentTimeMillis();
      Path dir = FileSystems.getDefault().getPath( filePath );
      DirectoryStream<Path> stream = Files.newDirectoryStream( dir );
      for (Path path : stream) {
        System.out.println( "" + i + ": " + path.getFileName() );
        if (++i > maxFiles) break;
      }
      stream.close();
      long stop = System.currentTimeMillis();
      System.out.println( "Elapsed: " + (stop - start) + " ms" );
    }
}
