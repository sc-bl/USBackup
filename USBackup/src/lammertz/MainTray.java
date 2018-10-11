package lammertz;

import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Toolkit;

public class MainTray
{
	public MainTray()
	{
		SystemTray tray = SystemTray.getSystemTray();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.getImage("TrayIcon.png");
		PopupMenu menu = new PopupMenu();
		
		
		
		
		TrayIcon icon = new TrayIcon(image,"Backup",menu);
		icon.setImageAutoSize(true);
	}
}
