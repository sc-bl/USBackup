package hillermann.kopiertest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarInputStream;

public class Performance
{
   String sourcePath = "E:\\stresstest.dat";
   String drainPath  = "U:\\gesicherte Dateien\\USBackupDaten";
   public Performance ()
   {
      long startzeit;
      long endezeit;
      long filesize = (new File(sourcePath)).length();
      String rate;

      startzeit = System.currentTimeMillis();
      copyNIO ();
      endezeit = System.currentTimeMillis();
      rate = String.format ( "%10d", (filesize/(endezeit-startzeit)*1000) );
      System.out.println ( "Kopieren NIO-Copy: " 
                         + rate 
                         + " Byte / Sekunde - "
                         + (endezeit-startzeit));

      
      startzeit = System.currentTimeMillis();
      copyBasic ();
      endezeit = System.currentTimeMillis();
      rate = String.format ( "%10d", (filesize/(endezeit-startzeit)*1000) );
      System.out.println ( "Kopieren Basic   : " 
                         + rate 
                         + " Byte / Sekunde - "
                         + (endezeit-startzeit));

      startzeit = System.currentTimeMillis();
      copyBuffered ();
      endezeit = System.currentTimeMillis();
      rate = String.format ( "%10d", (filesize/(endezeit-startzeit)*1000) );
      System.out.println ( "Kopieren Buffered: " 
                         + rate 
                         + " Byte / Sekunde - "
                         + (endezeit-startzeit));

}

   public static void main ( String[] args )
   {
      new Performance ();
   }

   private void copyBasic()
   {
      byte[] buffer = new byte[4096];
      int    count;
      FileInputStream  fis = null;
      FileOutputStream fos = null;
      try
      {
         fis = new FileInputStream ( sourcePath );
         fos = new FileOutputStream ( drainPath + "\\basic.dat" );
         
         while ( true )
         {
            count = fis.read ( buffer );
            if ( count == -1 ) break;
            fos.write ( buffer, 0, count );
         }
      }
      catch ( FileNotFoundException e )
      {
         e.printStackTrace();
      }
      catch ( IOException e )
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            if ( fis != null )
               fis.close ();
            if ( fos != null )
               fos.close ();
         }
         catch ( IOException e )
         {
            e.printStackTrace();
         }
      }
   }
      
   private void copyBuffered()
   {
      int byteWert;

      BufferedInputStream  bis = null;
      BufferedOutputStream bos = null;
      try
      {
         bis = new BufferedInputStream  ( new FileInputStream ( sourcePath ) );
         bos = new BufferedOutputStream ( new FileOutputStream ( drainPath + "\\buffered.dat" ) );
         
         while ( (byteWert = bis.read ()) != -1 )
         {
            bos.write ( byteWert );
         }
      }
      catch ( FileNotFoundException e )
      {
         e.printStackTrace();
      }
      catch ( IOException e )
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            if ( bis != null )
               bis.close ();
            if ( bos != null )
               bos.close ();
         }
         catch ( IOException e )
         {
            e.printStackTrace();
         }
      }
   }

   private void copyNIO()
   {
      int byteWert;
      CopyOption[] options = new CopyOption[] {StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES};
      try
      {
         Files.copy ( Paths.get ( sourcePath ),
                      Paths.get ( drainPath + "\\nio.dat" ),
                      options );
      }
      catch ( IOException e )
      {
         e.printStackTrace();
      }
   }
}
