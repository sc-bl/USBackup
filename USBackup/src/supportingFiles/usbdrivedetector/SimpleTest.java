package supportingFiles.usbdrivedetector;

import supportingFiles.usbdrivedetector.events.IUSBDriveListener;
import supportingFiles.usbdrivedetector.events.USBStorageEvent;

/**
 * @author samuelcampos
 */
public class SimpleTest implements IUSBDriveListener
{
   public static void main ( String[] args )
   {
      System.out.println ( "Start Test" );
      USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager ();

      driveDetector.getRemovableDevices ().forEach ( System.out::println );

      SimpleTest sTest = new SimpleTest ();

      driveDetector.addDriveListener ( sTest );

      try
      {
         Thread.sleep ( 120000 );
      }
      catch ( InterruptedException e )
      {
         e.printStackTrace ();
      }

      System.out.println ( "Test finished" );
   }

   private SimpleTest ()
   {

   }

   @Override
   public void usbDriveEvent ( USBStorageEvent event )
   {
      System.out.println ( "SimpleTest: - " + event );
   }
}
