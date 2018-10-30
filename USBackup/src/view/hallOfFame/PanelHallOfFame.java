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
      this.setHintergrund ( ViewHelper.getImage ( "images/eisen.jpg" ) );
      this.setLayout ( new BorderLayout ( 10, 10 ) );
      
      JLabel label = new JLabel ( "<html> <br><h3>&nbsp &nbsp Die Entwickler:" );
      
      JPanel center = new JPanel ();
      center.setOpaque ( false );
      center.setLayout ( new GridLayout( 1, 4, 10, 10 ) );
      
      JLabel bryan   = new JLabel ( ViewHelper.getImageIcon ( "images/Bryan.jpg" ) );
      JLabel michael   = new JLabel ( ViewHelper.getImageIcon ( "images/Michael.jpg" ) );
      JLabel nico   = new JLabel ( ViewHelper.getImageIcon ( "images/Nico.jpg" ) );
      JLabel simon   = new JLabel ( ViewHelper.getImageIcon ( "images/Simon.jpg" ) );
      
      bryan.setToolTipText   ( "<html><b>Bryan</b><br>Schreibt die beste Doku<br>Naja, die einzige ..." );
      michael.setToolTipText ( "<html><b>Michael</b><br>Heimlicher Projektleiter" );
      nico.setToolTipText    ( "<html><b>Nico</b><br>Könnte, was er machen sollte" );
      simon.setToolTipText   ( "<html><b>Simon</b><br>Meister des Repository ...<br>... irgendwann" );
      
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
