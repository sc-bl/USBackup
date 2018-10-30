package controller.main;

import java.util.UUID;

import view.TrayUI.TrayIconUtil;
import view.ereignisanzeige.EventLog;

public class MainController
{
   
   public MainController ()
   {
      UUID.randomUUID ();
      TrayIconUtil.showWaitingForStick ();
      TrayIconUtil.logEvent ( EventLog.INFO, "Programm gestartet" );
      try { Thread.sleep ( 5000 ); } catch ( InterruptedException e ) { }
      
      TrayIconUtil.showStickFound ();
      TrayIconUtil.logEvent ( EventLog.INFO, "Stick gefunden" );
      try { Thread.sleep ( 5000 ); } catch ( InterruptedException e ) { }
      
      TrayIconUtil.showPerformingBackup ();
      TrayIconUtil.logEvent ( EventLog.INFO, "Backup gestartet" );
      try { Thread.sleep ( 10000 ); } catch ( InterruptedException e ) { }

      TrayIconUtil.schowNothingToDo ();
      TrayIconUtil.logEvent ( EventLog.INFO, "Backup für heute abgeschlossen." );
      TrayIconUtil.logEvent ( EventLog.DEBUG, "Wenn Debuggt wird, erscheinen diese Meldungen mit blauer Kennung." );
      TrayIconUtil.logEvent ( EventLog.WARNING, "Warnungen kann man auch loswerden, sogar bei ganz langen Texten: \nsdjdk hskdfh skdjfh skjfh skjdhf ksjdfh ksjdhf ksjdhf ksdjfh ksdjfh ksjfh ksjdh ksjdh ksjdh ksjh ksjh ksjdh ksj hskdjh skj hskjdh skdjh skjdh skdjh sjkd hskhj skdjh skjdfhksj kfgjfkhiruzg xkfhksd ." );
      TrayIconUtil.logEvent ( EventLog.ERROR, "Fehler sind rot." );
      TrayIconUtil.logEvent ( EventLog.INFO, "Backup für heute abgeschlossen." );
      TrayIconUtil.logEvent ( EventLog.INFO, "Backup für heute abgeschlossen." );

   }

   public static void main ( String[] ags )
   {
      new MainController ();
   }
}
