package supportingFiles;

/**
 * Some constants used  .<br> 
 */
public class Constants
{
   /** State machine - Searching for stick */
   public static final String STATE_WAITING_FOR_STICK = "waitingForStick";

   /** State machine - Stick found. Checking whether backup is nessesary */
   public static final String STATE_STICK_FOUND = "stickFound";

   /** State machine - Performing Backup */
   public static final String STATE_PERFORMING_BACKUP = "performingBackup";

   /** State machine - Idle. No action required or performed */
   public static final String STATE_NOTHING_TO_DO = "nothingToDo";
   
   /** MessagePanel - Info message dispayed in black */
   public static final String MESSAGE_INFO    = "Info";

   /** MessagePanel - Debug message dispayed in blue */
   public static final String MESSAGE_DEBUG   = "Debug";

   /** MessagePanel - Warning message dispayed in yellow */
   public static final String MESSAGE_WARNING = "Warning";

   /** MessagePanel - Error message dispayed in red */
   public static final String MESSAGE_ERROR   = "Error";

}
