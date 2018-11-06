package controller;

import java.util.UUID;

import view.TrayUI.TrayIconUtil;
import view.ereignisanzeige.EventLog;

public class MainController
{
   
   public MainController ()
   {
      UUID.randomUUID ();
      TrayIconUtil.setState ( supportingFiles.Constants.STATE_WAITING_FOR_STICK ); 
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Programm gestartet" );
      try { Thread.sleep ( 5000 ); } catch ( InterruptedException e ) { }
      
      TrayIconUtil.setState ( supportingFiles.Constants.STATE_STICK_FOUND ); 
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Stick gefunden" );
      try { Thread.sleep ( 5000 ); } catch ( InterruptedException e ) { }
      
      TrayIconUtil.setState ( supportingFiles.Constants.STATE_PERFORMING_BACKUP );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Backup gestartet" );
      try { Thread.sleep ( 10000 ); } catch ( InterruptedException e ) { }

      TrayIconUtil.setState ( supportingFiles.Constants.STATE_NOTHING_TO_DO );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Backup f�r heute abgeschlossen." );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_DEBUG, "Wenn Debuggt wird, erscheinen diese Meldungen mit blauer Kennung." );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_WARNING, "Warnungen kann man auch loswerden, sogar bei ganz langen Texten: \nsdjdk hskdfh skdjfh skjfh skjdhf ksjdfh ksjdhf ksjdhf ksdjfh ksdjfh ksjfh ksjdh ksjdh ksjdh ksjh ksjh ksjdh ksj hskdjh skj hskjdh skdjh skjdh skdjh sjkd hskhj skdjh skjdfhksj kfgjfkhiruzg xkfhksd ." );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_ERROR, "Fehler sind rot." );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Backup f�r heute abgeschlossen." );
      TrayIconUtil.logEvent ( supportingFiles.Constants.MESSAGE_INFO, "Backup f�r heute abgeschlossen." );

   }

   public static void main ( String[] ags )
   {
      new MainController ();
   }
}
