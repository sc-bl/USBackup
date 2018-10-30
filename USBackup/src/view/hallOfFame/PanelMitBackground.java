package view.hallOfFame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Wenn ein Hintergrundbild vorhanden ist, wird dieses automatisch als Kachel
 * über die gesamte sichtbare Oberfläche des JPanels gezeichnet.
 * 
 * Das gewünschte Bild kann mit setHintergrund ( Image bild ) eingestellt werden.
 * 
 * Alle Methoden des normalen JPanel bleiben unverändert nutzbar.
 * 
 */
public class PanelMitBackground extends JPanel
{
   private static final long serialVersionUID = 1L;
   
   /**
    * Das Hintergrundbild wird (als Kachel) auf die gesamte Panelfläche gezeichnet.  
    */
   private Image hintergrund = null;
   
   /**
    * Setze das Bild, das als Hintergrund verwendet werden soll.
    * 
    * @param hintergrund Das Hintergrundbild.
    */
   public void setHintergrund ( Image hintergrund )
   {
      this.hintergrund = hintergrund;
   }

   /**
    * Gibt das aktuell eingestellte Hintergrundbild zurück.
    * 
    * @return Das Hintrgrundbild. "null", wenn nicht vorhanden.
    */
   public Image getHintergrund ()
   {
      return this.hintergrund;
   }

   /**
    * Überschreibt die paintComponent-Methode des Standard-Panels.
    * Wenn ein Hintergrundbild vorhanden ist, wird dieses automatisch als Kachel
    * über die gesamte sichtbare Oberfläche des Jpanels gezeichnet. 
    */
   public void paintComponent( Graphics g )
   {
      super.paintComponent ( g );

      int xHintergrund;
      int yHintergrund;

      if ( hintergrund == null )
         return;
      if ( (xHintergrund = hintergrund.getWidth (null)) == 0 )
         return;
      if ( (yHintergrund = hintergrund.getHeight(null)) == 0 )
         return;

      int xFaktor = (this.getWidth() +xHintergrund-1) / xHintergrund;
      int yFaktor = (this.getHeight()+yHintergrund-1) / yHintergrund;
      
      for ( int xCount=0; xCount<xFaktor; xCount++ )
         for ( int yCount=0; yCount<yFaktor; yCount++ )
            g.drawImage ( hintergrund, xCount*xHintergrund, yCount*yHintergrund, this );
   }
}
