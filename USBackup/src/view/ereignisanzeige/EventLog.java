package view.ereignisanzeige;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Displays messages as a table.<br>
 * Using different colurs depending of message type<br>
 * See constant values of this class
 *  
 * @author hillermann
 */
public class EventLog extends JPanel
{
   private static final long serialVersionUID = 8790982983791729227L;

   /** Info message dispayed in black */
   public static final String INFO    = "Info";

   /** Debug message dispayed in blue */
   public static final String DEBUG   = "Debug";

   /** Warning message dispayed in yellow */
   public static final String WARNING = "Warning";

   /** Error message dispayed in red */
   public static final String ERROR   = "Error";
   
   private SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( " yyyy.MM.dd - HH:mm:ss ");
   
   // Basiseinstellungen für alle grafischen Objekte
   private GridBagConstraints gbc = new GridBagConstraints ( 0,                             // int gridx
                                                             0,                             // int gridy
                                                             1,                             // int gridwidth
                                                             1,                             // int gridheight
                                                             0,                             // double weightx
                                                             0,                             // double weighty
                                                             GridBagConstraints.PAGE_START, // int anchor
                                                             GridBagConstraints.BOTH,       // int fill
                                                             new Insets ( 2, 2, 2, 2 ),     // Insets insets
                                                             0,                             // int ipadx
                                                             1 );                           // int ipady
   
   private JPanel    gridBagPanel;
   private Component dummy;
   private int       yCounter = 0; // Zeilenangabe für die Meldungen
   
   /**
    * The MessagePanel will show messages in different colours. Colours depend on the type of param "type".<br>
    * Possible values are:<br>
    * INFO - black - information message<br>
    * DEBUG - blue - debug message<br>
    * WARNING - orange - warning message<br>
    * ERROR - red - error message<br><br>
    *  
    * @param type the type of the message defined as constants of this class.<br>
    * @param message the message to be displayed
    */
   public void addMessage ( String type, String message)
   {
      this.internAddMessage ( type, message );
   }

   /**
    * Standardkonstruktor erzeugt ein JPanel, das in einer beliebigen GUI eingefügt werden kann
    */
   public EventLog () 
   {
      this.setLayout ( new BorderLayout() );

      // Inneres Panel, das gescrollt werden soll
      // Darauf werden die Nachrichten dargestellt
      gridBagPanel = new JPanel ( new GridBagLayout () )
      {
         private static final long serialVersionUID = 731083325864695403L;

         public Dimension getPreferredSize( )
         {
            Dimension d = super.getPreferredSize ();
            d.width = 0;
            return d;
         }         
      };
      gridBagPanel.setBackground ( Color.lightGray );
      
      // Die ScrollPane nimmt die gesamte Fläche ein
      JScrollPane jsp = new JScrollPane ( gridBagPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
      add ( jsp, BorderLayout.CENTER);
      
      // Ein Dummy-Element für den freien Platz an der letzten möglichen Position einfügen
      addDummy ();
   }

   private void addDummy()
   {
      gbc.gridx   = 0;
      gbc.gridy   = 511; // Leider sind nur 512 Zeilen möglich
      gbc.weighty = 0.1;
      dummy = new JLabel(" ");
      gridBagPanel.add ( dummy, gbc );
      gbc.weighty = 0;
   }
   
   private void internAddMessage ( String type, String message)
   {
      Color color;
      
      if ( type == null ) type = "";
       
      switch ( type )
      {
         case INFO:    color = Color.black;  break;
         case DEBUG:   color = Color.blue;   break;
         case WARNING: color = Color.orange; break;
         case ERROR:   color = Color.red;    break;
         default:      color = Color.black;  type = "Unknown"; break;
      }
      
      JLabel label = new JLabel ( simpleDateFormat.format ( new Date() )+" ["+type+"]: " );
      label.setForeground ( color );
      label.setBackground ( Color.WHITE );
      label.setOpaque ( true );
      gbc.gridx   = 0;
      gbc.gridy   = yCounter ;
      gbc.weightx = 0;

      gridBagPanel.add ( label, gbc );
      
      JTextArea text = new JTextArea ( message );
      text.setEditable ( false );
      text.setLineWrap ( true );
      text.setWrapStyleWord ( true );
      // text.setForeground ( color );
      text.setBackground ( Color.white );
      gbc.gridx   = 1;
      gbc.gridy   = yCounter ;
      gbc.weightx = 0.1;
      gridBagPanel.add ( text, gbc );
      
      yCounter++;

      // Höhe des Panels aus der aktuellen Höhe aller Komponenten berechnen
      gridBagPanel.doLayout ();
      
      // Autoscroll
      gridBagPanel.scrollRectToVisible ( text.getBounds () );
      gridBagPanel.scrollRectToVisible ( text.getBounds () );
   }
}
