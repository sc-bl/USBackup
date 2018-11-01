package view.hallOfFame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHallOfFame extends PanelMitBackground
{
   private static final long serialVersionUID = 494848165202899437L;

   public PanelHallOfFame ()
   {
      this.setHintergrund ( ViewHelper.getImage ( "supportingFiles/images/eisen.jpg" ) );
      this.setLayout ( new BorderLayout ( 10, 10 ) );
      
      JLabel label = new JLabel ( "<html> <br><h3>&nbsp &nbsp Die Entwickler:" );
      
      JPanel center = new JPanel ();
      center.setOpaque ( false );
      center.setLayout ( new GridLayout( 1, 4, 10, 10 ) );
      
      JLabel bryan   = new JLabel ( ViewHelper.getImageIcon ( "supportingFiles/images/Bryan.jpg" ) );
      JLabel michael   = new JLabel ( ViewHelper.getImageIcon ( "supportingFiles/images/Michael.jpg" ) );
      JLabel nico   = new JLabel ( ViewHelper.getImageIcon ( "supportingFiles/images/Nico.jpg" ) );
      JLabel simon   = new JLabel ( ViewHelper.getImageIcon ( "supportingFiles/images/Simon.jpg" ) );
      
      bryan.setToolTipText   ( "<html><b>Bryan</b>" );
      michael.setToolTipText ( "<html><b>Michael</b>" );
      nico.setToolTipText    ( "<html><b>Nico</b>" );
      simon.setToolTipText   ( "<html><b>Simon</b>" );
      
      center.add ( bryan );
      center.add ( michael );
      center.add ( nico );
      center.add ( simon );
      
      this.add ( label, BorderLayout.NORTH );
      this.add ( center, BorderLayout.CENTER );
      this.add ( new JLabel ( " " ), BorderLayout.SOUTH );
      this.add ( new JLabel ( " " ), BorderLayout.EAST );
      this.add ( new JLabel ( " " ), BorderLayout.WEST );
   }
}
