import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.Format.Field;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.Kelime;
import net.zemberek.yapi.KelimeTipi;
import net.zemberek.yapi.Kok;
import javax.swing.JLabel;


public class ProjectGUI extends JFrame {

	private JPanel contentPane;
	private final JButton fixButton = new JButton("Automatic Fix");
	private final JTextPane textPane = new JTextPane();
	StyledDocument doc = textPane.getStyledDocument();
	private final JButton btnClear2 = new JButton("Clear");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectGUI frame = new ProjectGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	Zemberek z = new Zemberek(new TurkiyeTurkcesi());
	private final JTextPane textPaneW = new JTextPane();
	private final JButton btnClear1 = new JButton("Clear");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	private final JLabel lblWrongWords = new JLabel("Wrong words:");
	private final JLabel lblYourText = new JLabel("Your text:");
	public ProjectGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		fixButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textPane.getText();
				String birlesim = "";
				String wrong = "";
				
				for(String retval : str.split(" ", 0)){
					if (!z.kelimeDenetle(retval)) {
					for (String s : z.oner(retval)){
						System.out.println(s);
						wrong += retval + " to " + s + "\r\n"; 
						textPaneW.setText(wrong);
						birlesim += " " + s;
						break;
				        }
					}else
						birlesim += " " + retval; 
				}
				
				textPane.setText(birlesim);
			}
		});
		fixButton.setBounds(214, 282, 117, 23);
		
		contentPane.add(fixButton);

		btnClear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaneW.setText("");
			}
		});
		
		
		btnClear2.setBounds(439, 282, 89, 23);
		
		contentPane.add(btnClear2);
		btnClear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText("");
			}
		});
		btnClear1.setBounds(36, 282, 89, 23);
		
		contentPane.add(btnClear1);
		scrollPane.setBounds(36, 82, 382, 192);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textPane);
		scrollPane_1.setBounds(428, 82, 115, 192);
		
		contentPane.add(scrollPane_1);
		textPaneW.setForeground(Color.RED);
		scrollPane_1.setViewportView(textPaneW);
		lblWrongWords.setBounds(439, 57, 89, 14);
		
		contentPane.add(lblWrongWords);
		lblYourText.setBounds(46, 57, 65, 14);
		
		contentPane.add(lblYourText);
	}
}


