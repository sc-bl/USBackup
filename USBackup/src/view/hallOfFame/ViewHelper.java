package view.hallOfFame;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ViewHelper
{
   
   public static ImageIcon getImageIcon ( String path )
   {
      try
      {
         // Zuerst feststellen, wo sich mein JAR-Archiv befindet
         URL iconURL = ClassLoader.getSystemClassLoader ().getResource ( path );

         ImageIcon imageIcon = new ImageIcon ( iconURL );

         // Dann das Icon aus dem Archiv laden
         return imageIcon;
      }
      catch ( Exception e )
      {
         e.printStackTrace ();
         return null;
      }
   }

   public static Image getImage ( String path )
   {
      try
      {
         return ViewHelper.getImageIcon ( path ).getImage ();
      }
      // Wenn Methode "null" zurückgibt...
      catch ( Exception e )
      {
         e.printStackTrace ();
         return null;
      }
   }

}
