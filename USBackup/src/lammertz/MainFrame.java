package lammertz;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.UIManager;

import controller.main.MainController;
import hillermann.OptionFrame;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainFrame
{
	private JFrame mainFrame;
	private JButton btnOptions;
	private JButton btnRestoreBackup;
	private JButton btnExecuteBackup;
	private JLabel lblLetztesBackup;
	
	public MainFrame()
	{
		mainFrame = new JFrame();
		mainFrame.setTitle("Men\u00FC");
		mainFrame.getContentPane().setBounds(new Rectangle(0, 0, 200, 200));
		mainFrame.setBounds(new Rectangle(2, 0, 200, 200));
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		
		try
		{
			UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e){}
		
		
		
		btnOptions = new JButton("Optionen");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				OptionFrame optionFrame = new OptionFrame();
			}
		});
		btnOptions.setBounds(24, 115, 149, 23);
		mainFrame.getContentPane().add(btnOptions);
		
		btnExecuteBackup = new JButton("Backup starten");
		btnExecuteBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ExecutorService service = Executors.newFixedThreadPool(2); //Aufruf zum Background Thread //Erstmal nur temporär, da im MainController sleeper aufrufe gemacht werden, welche die Navigation Blockiert
			    service.submit(new Runnable() { //
			        public void run() { //
			        	MainController.main(null); //Zeigt das Tray-Icon an
			        }
			    });
				
			}
		});
		btnExecuteBackup.setBounds(24, 47, 149, 23);
		mainFrame.getContentPane().add(btnExecuteBackup);
		
		btnRestoreBackup = new JButton("Backups Anzeigen");
		btnRestoreBackup.setBounds(24, 81, 149, 23);
		mainFrame.getContentPane().add(btnRestoreBackup);
		
		lblLetztesBackup = new JLabel("Letztes Backup: 10.10.2018");
		lblLetztesBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetztesBackup.setBounds(24, 11, 149, 14);
		mainFrame.getContentPane().add(lblLetztesBackup);
		
		mainFrame.setVisible(true);
	}
}
