package view.TrayUI;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.ereignisanzeige.EventFrame;
import view.hallOfFame.DialogHallOfFame;

public class TrayIconUtil
{
   /** The ActionCommand returned when exit is selected */
   public static final int ACTION_EXIT = 1;
   
   /** The ActionCommand returned when about is selected */
   public static final int ACTION_ABOUT = 2;

   private static final TrayIcon   trayIcon = initTrayIcon ();         // Singleton!
   private static final EventFrame logFrame = new EventFrame ( null ); // Singleton!

   private static SystemTray systemTray;
   private static PopupMenu  popupMenu;
   
   private static Image iRed;
   private static Image iGreen;
   private static Image iYellow;
   private static Image iCopy1;
   private static Image iCopy2;
   private static Image iCopy3;
   private static Image iCopy4;
   
   private static Thread imageThread;

   private TrayIconUtil () {}

   public static synchronized void setState ( String state )
   {
      switch ( state )
      {
         case supportingFiles.Constants.STATE_WAITING_FOR_STICK:
            showWaitingForStick ();
            break;
         case supportingFiles.Constants.STATE_STICK_FOUND:
            showStickFound ();
            break;
         case supportingFiles.Constants.STATE_PERFORMING_BACKUP:
            showPerformingBackup ();
            break;
         case supportingFiles.Constants.STATE_NOTHING_TO_DO:
            showNothingToDo ();
            break;
         default:   
            showWaitingForStick ();
            break;
      }
   }
   
   public static synchronized void  logEvent ( String eventType, String message )
   {
      if ( logFrame != null )
         logFrame.logEvent( eventType, message );
      else
         System.out.println ( "Message, bevor TrayIcon initialisiert wurde" );
   }

   
   /////////////////////////////////////////////////////////////////////////////
   // Ab hier ist alles "private" und darf nach Belieben verändert werden.
   /////////////////////////////////////////////////////////////////////////////
   private static TrayIcon getTrayIcon()
   {
      //if ( trayIcon == null )
         //initTrayIcon ();
      return trayIcon;
   }
   
   private static TrayIcon initTrayIcon ()
   {
      // Check the SystemTray support
      if ( !SystemTray.isSupported () )
      {
         JOptionPane.showMessageDialog ( null, "SystemTray is not supported", "USBackup", JOptionPane.ERROR_MESSAGE );
         System.exit ( 0 );
      }
      
      // Get SystemTray
      systemTray = SystemTray.getSystemTray ();
      
      // Get Ressources for SystemTrayIcon
      iRed    = createImage ( "supportingFiles/images/bred.gif" );
      iGreen  = createImage ( "supportingFiles/images/bgreen.gif" );
      iYellow = createImage ( "supportingFiles/images/byellow.gif" );
      iCopy1  = createImage ( "supportingFiles/images/bcopy1.gif" );
      iCopy2  = createImage ( "supportingFiles/images/bcopy2.gif" );
      iCopy3  = createImage ( "supportingFiles/images/bcopy3.gif" );
      iCopy4  = createImage ( "supportingFiles/images/bcopy4.gif" );
      
      TrayIcon trayIcon = new TrayIcon ( iYellow, "Warte auf g\u00fcltigen USB-Stick" );
      trayIcon.setImageAutoSize ( true );

      SwingUtilities.invokeLater 
      ( 
        new Runnable () 
        {
           public void run () { createAndShowGUI (); } 
        }
      );
      
      return trayIcon;
   }

   private static void createAndShowGUI ()
   {
      // Create visible components
      setLookAndFeel ();
      
      // Create main menu
      popupMenu = new PopupMenu ();
      
      // Create popup menu components
      MenuItem aboutItem = new MenuItem ( "Programm-Info" );
      MenuItem fameItem  = new MenuItem ( "Hall of fame" );
      MenuItem logItem   = new MenuItem ( "Ereignisprotokoll" );
      MenuItem exitItem  = new MenuItem ( "Programm beenden" );

      // Add components to popup menu
      popupMenu.add ( aboutItem );
      popupMenu.addSeparator ();
      popupMenu.add ( fameItem );
      popupMenu.addSeparator ();
      popupMenu.add ( logItem );
      popupMenu.addSeparator ();
      popupMenu.addSeparator ();
      popupMenu.add ( exitItem );

      trayIcon.setPopupMenu ( popupMenu );

      try
      {
         systemTray.add ( trayIcon );
      }
      catch ( AWTException e )
      {
         JOptionPane.showMessageDialog ( null, "TrayIcon could not be added.", "USBackup", JOptionPane.ERROR_MESSAGE );
         System.exit ( 0 );
      }

      trayIcon.addMouseListener (  new MouseListener()
      {
         public void mouseReleased ( MouseEvent e ) {}
         public void mousePressed ( MouseEvent e )  {}
         public void mouseExited ( MouseEvent e ) {}
         public void mouseEntered ( MouseEvent e ) {}
         public void mouseClicked ( MouseEvent e )
         {
            if ( e.getButton () == MouseEvent.BUTTON1) // Linker Maus-Button
               JOptionPane.showMessageDialog ( null, "This dialog box is run from System Tray" );
         }
      } );

      aboutItem.addActionListener ( new ActionListener ()
      {
         public void actionPerformed ( ActionEvent e )
         {
            JOptionPane.showMessageDialog ( null, "<html><H1>USBackup</h1>\n"
                  + "Version " + supportingFiles.ProgramConfigurations.VERSION_NUMBER + "\n"
                  + "Berufskolleg Alsdorf\n"
                  + "52477 Alsdorf, Heidweg 2\n"
                  + "<html><b>www.bk-alsdorf.de</b>\n"
                  + "\u00a9 IT 18 B",
                  "USBackup", JOptionPane.INFORMATION_MESSAGE );
         }
      } );

      fameItem.addActionListener ( new ActionListener ()
      {
         public void actionPerformed ( ActionEvent e )
         {
            new DialogHallOfFame ();
         }
      } );


      logItem.addActionListener ( new ActionListener ()
      {
         public void actionPerformed ( ActionEvent e )
         {
            logFrame.setVisible ( true );;
         }
      } );

      exitItem.addActionListener ( new ActionListener ()
      {
         public void actionPerformed ( ActionEvent e )
         {
            systemTray.remove ( trayIcon );
            System.exit ( 0 );
         }
      } );
   }

   /**
    * Create image object from image file
    * 
    * @param path Absolute or relative path to image to be loaded
    * @return     The image object - null if no image found 
    */
   private static Image createImage ( String path )
   {
      URL imageURL = ClassLoader.getSystemClassLoader ().getResource ( path );
      if ( imageURL == null )
      {
         System.err.println ( "Resource not found: " + path );
         JOptionPane.showMessageDialog ( null, "TrayImage not found", "USBackup", JOptionPane.ERROR_MESSAGE );
         System.exit ( 0 );
         return null; // Der blöde Compiler will das so, auch wenn es keinen Sinn macht
      }
      else
      {
         return ( new ImageIcon ( imageURL ) ).getImage ();
      }
   }

   /**
    * Set Look & Feel to current system style.
    * Will be "Metal", if no system style is supported. 
    */
   private static void setLookAndFeel ( )
   {
      try /* Use an appropriate Look and Feel */
      {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch ( Exception ex )
      {
         ex.printStackTrace (); // Dann nehmen wir halt das default
      }
   }
   
   /**
    * Kill thread for animated icon
    * Set icon to red
    */
   private static void showWaitingForStick ()
   {
      if ( imageThread != null ) imageThread.interrupt ();
      getTrayIcon ().setImage ( iRed );
      getTrayIcon ().setToolTip ( "Warte auf g\u00fcltigen USB-Stick" );
   }

   /**
    * Kill thread for animated icon
    * Set icon to yellow
    */
   private static void showStickFound ()
   {
      if ( imageThread != null ) imageThread.interrupt ();
      getTrayIcon ().setImage ( iYellow );
      getTrayIcon ().setToolTip ( "Stick f\u00fcr Backup gefunden" );
   }

   /**
    * Start thread for animated icon (changing colours) 
    */
   private static void showPerformingBackup ()
   {
      if ( imageThread != null ) imageThread.interrupt ();
      getTrayIcon ().setToolTip ( "Backup wird durchgef\u00fchrt" );
      
      imageThread = new Thread ()
      {
         public void run()
         {
            getTrayIcon ().setImage ( iYellow );
            try
            {
               while ( true )
               {
                  sleep ( 500 );
                  getTrayIcon ().setImage ( iCopy1 );
                  sleep ( 500 );
                  getTrayIcon ().setImage ( iCopy2 );
                  sleep ( 500 );
                  getTrayIcon ().setImage ( iCopy3 );
                  sleep ( 500 );
                  getTrayIcon ().setImage ( iCopy4 );
               }
            }
            catch ( InterruptedException e )
            {
               imageThread = null;
            }
         }
      };
      imageThread.start ();
   }

   /**
    * Kill thread for animated icon
    * Set icon to green
    */
private static void showNothingToDo ()
   {
      if ( imageThread != null ) imageThread.interrupt ();
      getTrayIcon ().setImage ( iGreen );
      getTrayIcon ().setToolTip ( "Backup wurde heute schon durchgef\u00fchrt" );
   }

   /////////////////////////////////////////////////////////////////////////////////////////
   // public methods
   /////////////////////////////////////////////////////////////////////////////////////////
}

