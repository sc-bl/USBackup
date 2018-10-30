package model;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import supportingFiles.ProgramConfigurations;
import view.GUI;
import view.TrayUI.TrayIconUtil;

public class UUIDHelper
{
   private static LinkOption[] linkOption = new LinkOption[] { LinkOption.NOFOLLOW_LINKS };
   
   private static CopyOption[] copyOption = new CopyOption[] { // StandardCopyOption.ATOMIC_MOVE,      // Nur beim Umbenennen verwenden!
                                                               // StandardCopyOption.COPY_ATTRIBUTES,  // Attribute mit �bernehmen
                                                               // StandardCopyOption.REPLACE_EXISTING, // �berschreiben, wenn schon vorhanden
                                                               LinkOption.NOFOLLOW_LINKS };         // OriginalLink, nicht die verkn�pfte Datei 

   private static boolean dos  = false;
   private static boolean posix = false;

   /**
    * Creates a hidden and write protected file on target device containing a random UUID.<br>
    * If this file exists, it will be replaced when not a directory.
    * 
    * @param <b>targetName</b> root file (example: <b>F:</b> for windows).
    * @return <b>true</b> if file was successfully created
    */
   public UUID initUSBDriveCreateUUID ( String targetName )
   {
      Path ziel = Paths.get ( targetName, ProgramConfigurations.UUID_FILE_NAME );

      // Pr�fen, ob schon eine Datei mit dem Namen vorhanden ist.
      if (Files.exists ( ziel, linkOption ))
      {
         // Wenn Verzeichnis mit diesem Namen existiert (unwahrscheinlich!)
         if ( Files.isDirectory ( ziel, linkOption ))
         {
            TrayIconUtil.logEvent ( GUI.ERROR, "Datei " + ziel + " ist ein Verzeichnisname und kann nicht angelegt werden." );
            return null;
         }
         // Wenn Datei, dann Schreibrechte holen
         else
         {
            if ( !Files.isWritable ( ziel ) )
               if ( !setHiddenAndProtected ( ziel, false ) )
                  return null;
         }
      }
 
      // UUID generieren und in Datei schreiben
      UUID uuid = UUID.randomUUID(); 
      try
      {
         ziel = Files.write ( ziel, uuid.toString().getBytes ()  );
      }
      catch ( Exception e )
      {
         TrayIconUtil.logEvent ( GUI.ERROR, "Datei " + ziel + " konnte nicht angelegt werden\n" + e.getMessage () );
         e.printStackTrace();
         return null;
      }

      // Datei sch�tzen
      if ( !setHiddenAndProtected ( ziel, true ) )
         return null;
      
      return uuid; 
   }
   
   // Zugriffsrechte f�r UUID-Datei �ndern
   private boolean setHiddenAndProtected ( Path path, boolean hideAndProtect )
   {
      // Herausfinden, um welches Dateisystem es sich handelt.
      if ( !checkFileSystem ( path ) ) 
         return false;
      
      if ( dos )
      {
         DosFileAttributeView dosView = Files.getFileAttributeView ( path, DosFileAttributeView.class );
         try
         {
            dosView.setHidden   ( hideAndProtect );
            dosView.setReadOnly ( hideAndProtect );
         }
         catch ( IOException e )
         {
            TrayIconUtil.logEvent ( GUI.ERROR, "Fehlende Rechte im Dateisystem\n" + e.getMessage () );
            e.printStackTrace();
            return false;
         }
      }
      else if ( posix )
      {
         PosixFileAttributeView posixView = Files.getFileAttributeView ( path, PosixFileAttributeView.class );
         Set<PosixFilePermission> permissions = new HashSet<>();
         if ( hideAndProtect )
         {
            permissions.add ( PosixFilePermission.GROUP_READ );
            // permissions.add ( PosixFilePermission.GROUP_WRITE );
            // permissions.add ( PosixFilePermission.GROUP_EXECUTE );
            permissions.add ( PosixFilePermission.OTHERS_READ );
            // permissions.add ( PosixFilePermission.OTHERS_WRITE );
            // permissions.add ( PosixFilePermission.OTHERS_EXECUTE );
            permissions.add ( PosixFilePermission.OWNER_READ );
            // permissions.add ( PosixFilePermission.OWNER_WRITE );
            // permissions.add ( PosixFilePermission.OWNER_EXECUTE );
         }
         else
         {
            permissions.add ( PosixFilePermission.GROUP_READ );
            permissions.add ( PosixFilePermission.GROUP_WRITE );
            // permissions.add ( PosixFilePermission.GROUP_EXECUTE );
            permissions.add ( PosixFilePermission.OTHERS_READ );
            permissions.add ( PosixFilePermission.OTHERS_WRITE );
            // permissions.add ( PosixFilePermission.OTHERS_EXECUTE );
            permissions.add ( PosixFilePermission.OWNER_READ );
            permissions.add ( PosixFilePermission.OWNER_WRITE );
            // permissions.add ( PosixFilePermission.OWNER_EXECUTE );
         }
            
         try
         {
            posixView.setPermissions(permissions);
         }
         catch ( IOException e )
         {
            TrayIconUtil.logEvent ( GUI.ERROR, "Fehlende Rechte im Dateisystem\n" + e.getMessage () );
            e.printStackTrace();
            return false;
         }
      }
      return true;
   }
   
   private boolean checkFileSystem ( Path path ) 
   {
      // Dateieigenschaften unabh�ngig vom Betriebssystem setzen
      try   // Teste, ob DOS oder Unix
      {
         dos   = Files.getFileStore ( path ).supportsFileAttributeView ( DosFileAttributeView.class );
         posix = Files.getFileStore ( path ).supportsFileAttributeView ( PosixFileAttributeView.class );
      }
      catch ( Exception e )
      {
         if ( ProgramConfigurations.DEBUG )
            TrayIconUtil.logEvent ( GUI.ERROR, "Dateisystem konnte nicht bestimmt werden\n" + e.getMessage () );
         e.printStackTrace();
         return false;
      }
      return true;
   }
   
   /**
    * Nur zu Testzwecken!
    * @param args
    */
   public static void main ( String[] args )
   {
      UUIDHelper uuidh = new UUIDHelper();
      
      System.out.println ( uuidh.initUSBDriveCreateUUID ( "F:" ) );
   }
}
