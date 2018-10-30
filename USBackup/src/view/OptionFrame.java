package view;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;

public class OptionFrame
{
	private JFrame optionFrame;
	private JButton btnBackupPath;
	private JTextField txtfBackupPath;
	private JButton btnBackupSavePath;
	private JTextField txtfBackupSavePath;
	private JCheckBox chAutoBackup;
	private JSeparator sep1;
	private JSeparator sep2;
	private JLabel lblText1;
	private JLabel lblText2;
	private JButton btnSave;
	private JButton btnCancel;
	
	
	
	public OptionFrame()
	{
		optionFrame = new JFrame("Optionen");
		optionFrame.setSize(411,254);
		optionFrame.setLocationRelativeTo(null);
		optionFrame.getContentPane().setLayout(null);
		optionFrame.setResizable(false);
		
		try
		{
			UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e){}
		
		btnBackupPath = new JButton("Ausw\u00E4hlen");
		btnBackupPath.setBounds(10, 37, 89, 23);
		btnBackupPath.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fChooser = new JFileChooser();
				fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fChooser.showOpenDialog(optionFrame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					txtfBackupPath.setText(fChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		optionFrame.getContentPane().add(btnBackupPath);
		
		txtfBackupPath = new JTextField();
		txtfBackupPath.setBounds(114, 38, 251, 20);
		optionFrame.getContentPane().add(txtfBackupPath);
		txtfBackupPath.setColumns(10);
		
		btnBackupSavePath = new JButton("Ausw\u00E4hlen");
		btnBackupSavePath.setBounds(10, 110, 89, 23);
		btnBackupSavePath.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fChooser = new JFileChooser();
				fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fChooser.showOpenDialog(optionFrame);
				
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					txtfBackupSavePath.setText(fChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		optionFrame.getContentPane().add(btnBackupSavePath);
		
		txtfBackupSavePath = new JTextField();
		txtfBackupSavePath.setBounds(114, 111, 251, 20);
		optionFrame.getContentPane().add(txtfBackupSavePath);
		txtfBackupSavePath.setColumns(10);
		
		chAutoBackup = new JCheckBox("Automatische Backups erlauben");
		chAutoBackup.setBounds(10, 158, 188, 23);
		optionFrame.getContentPane().add(chAutoBackup);
		
		sep1 = new JSeparator();
		sep1.setBounds(10, 71, 355, 14);
		optionFrame.getContentPane().add(sep1);
		
		sep2 = new JSeparator();
		sep2.setBounds(10, 144, 355, 14);
		optionFrame.getContentPane().add(sep2);
		
		lblText1 = new JLabel("Ordner der gesichert werden soll");
		lblText1.setBounds(20, 12, 345, 14);
		optionFrame.getContentPane().add(lblText1);
		
		lblText2 = new JLabel("Ordner wo die Sicherung gespeichert werden soll");
		lblText2.setBounds(20, 84, 345, 14);
		optionFrame.getContentPane().add(lblText2);
		
		btnSave = new JButton("Speichern");
		btnSave.setBounds(10, 188, 89, 23);
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(txtfBackupPath.getText().length() > 0 && txtfBackupSavePath.getText().length() > 0)
				{
					
					String backupPath = txtfBackupPath.getText().toString();
					String backupSavePath = txtfBackupSavePath.getText().toString();
					boolean isAutoBackup = chAutoBackup.isSelected();
					//AppSettings.getInstance().setBackupPath();
					
					optionFrame.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(optionFrame, "Bitte w�hlen Sie zwei Pfade aus !", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		optionFrame.getContentPane().add(btnSave);
		
		btnCancel = new JButton("Abbrechen");
		btnCancel.setBounds(296, 188, 89, 23);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				optionFrame.setVisible(false);
			}
		});
		optionFrame.getContentPane().add(btnCancel);
	
		optionFrame.setVisible(true);	
	}
}
