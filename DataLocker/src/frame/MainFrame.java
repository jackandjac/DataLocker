package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.io.*;
import java.util.*;

public class MainFrame extends JFrame {
	JRadioButton jrdEasy = new JRadioButton("Easy Mode");
	JRadioButton jrdAdv = new JRadioButton("Advance Mode");
	JComboBox jcba = new JComboBox();
	JComboBox jcbb = new JComboBox();
	JButton jbtStartEasy = new JButton("Start Encryption");
	JButton jbtStartAdv = new JButton("Start Encryption");
	JPanel enPanel = new JPanel();
	JPanel dePanel = new JPanel();
	HashMap<String, File> driveMapping = new HashMap<String, File>();

	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public MainFrame() {
		JTabbedPane jtp = new JTabbedPane();
		ButtonGroup group = new ButtonGroup();
		group.add(jrdEasy);
		group.add(jrdAdv);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		enPanel.setLayout(new GridLayout(2, 1));
		BasicPanel jp1 = new BasicPanel();
		AdvancedPanel jp2 = new AdvancedPanel();

		jp1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		jp2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		enPanel.add(jp1);
		enPanel.add(jp2);
		jtp.addTab("Encryption", enPanel);
		jtp.addTab("Decryption", dePanel);
		this.add(jtp);

	}

	class BasicPanel extends JPanel {
		public BasicPanel() {
			this.setLayout(new GridLayout(3, 4));
			int i = 3;
			int j = 3;
			JPanel[][] panelHolder = new JPanel[i][j];

			for (int m = 0; m < i; m++) {
				for (int n = 0; n < j; n++) {
					panelHolder[m][n] = new JPanel();
					add(panelHolder[m][n]);
				}
			}
			panelHolder[1][0].add(jrdEasy);
			panelHolder[1][1].add(jcba);
			panelHolder[1][1].add(jbtStartEasy);
		}
	}

	class AdvancedPanel extends JPanel {
		public AdvancedPanel() {
			this.setLayout(new GridLayout(3, 4));
			int i = 3;
			int j = 3;
			JPanel[][] panelHolder = new JPanel[i][j];

			for (int m = 0; m < i; m++) {
				for (int n = 0; n < j; n++) {
					panelHolder[m][n] = new JPanel();
					add(panelHolder[m][n]);
				}
			}
			panelHolder[1][0].add(jrdAdv);
			panelHolder[1][1].add(jcbb);
			panelHolder[1][1].add(jbtStartEasy);
			jrdAdv.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					
				}} );
		}
	}

	class UDiskDetector extends Thread {
		
		File basedDir = new File("/media/pi");

		public UDiskDetector() {

		}
		public void updateComboBox(){
			Set<String> keys= driveMapping.keySet();
			jcba.removeAllItems();
			jcbb.removeAllItems();
			for(String item:keys){
				jcba.addItem(item);
				jcbb.addItem(item);
			}
		}

		public void run() {

			Process p = null;
			while (true) {// while loop
				try {
					driveMapping.clear();
					p = Runtime.getRuntime().exec("ls " + basedDir);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(p.getInputStream()));
					String line = null;
					while ((line = br.readLine()) != null) {
						
						System.out.print("removable drives : " + line + "\n");
						if(!driveMapping.containsKey(line)){
							driveMapping.put(line, new File("/media/pi/"+line));
						}
					}
					
					sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

}
