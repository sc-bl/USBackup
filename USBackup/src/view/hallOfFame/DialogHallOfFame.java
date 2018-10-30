package view.hallOfFame;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JDialog;

public class DialogHallOfFame
{
   private JDialog dialog;

   public DialogHallOfFame ( )
   {
      Window window = null;

      dialog = new JDialog ( window );
      dialog.setTitle ( "USBackup - Hall of fame:" );
      dialog.setModal ( true );
      dialog.setDefaultCloseOperation ( JDialog.DISPOSE_ON_CLOSE );
      dialog.add ( new PanelHallOfFame (), BorderLayout.CENTER );
      dialog.pack ();
      dialog.setResizable ( false );
      dialog.setLocationRelativeTo ( null );
      dialog.setVisible ( true );
   }
}
