package view.ereignisanzeige;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class EventFrame
{
   private EventLog  logPanel = new EventLog();
   private JFrame    frame    = new JFrame ( "USBackup - EvenLog" );
   private Component parent;
   
   /**
    * Creates a protocol window.<br>
    * Initial state is "invisible". Use messagePanel.setVisible (boolean visible) to control visibility.<br>
    * <br>
    * Use static EventLog.addMessage (String type, String message) for adding messages
    * 
    * @param parent show this window relative to parent component or null for center of screeen
    */
   public EventFrame ( Component parent )
   {
      this.parent = parent;
      SwingUtilities.invokeLater 
      ( 
        new Runnable () 
        {
           public void run () { createAndShowGUI (); } 
        }
      );
   }
   
   private void createAndShowGUI ()
   {
      frame.setDefaultCloseOperation ( JFrame.HIDE_ON_CLOSE );
      frame.setBounds ( 0, 0, 800, 300 );
      frame.setLocationRelativeTo ( parent );
      frame.getContentPane ().add ( logPanel, BorderLayout.CENTER );
   }
   
   /**
    * Shows or hides this Window depending on the value of parameter visible. 
    * @param visible - if true, makes the Window visible, otherwise hides the Window.
    */
   public void setVisible ( boolean visible )
   {
      frame.setVisible ( visible );
   }
   
   public void logEvent ( String infoType, String message )
   {
      logPanel.addMessage ( infoType, message );
   }
}
