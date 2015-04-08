import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amountText;
	private JLabel amountLabel;
	private JLabel amountEx;
	private JLabel lblPowerRangers;
	private JButton count;
	private JPanel panel;
	private JLabel imageLabel;
	private String amount;
	private JButton btnUndo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public gui() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sari\\Documents\\Eclipse\\Dante\\spoc icon.png"));
		setTitle("Power Rangers Serial# Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][][][grow][][][][grow]", "[grow][][][][][][][][][][][][][][][][][]"));
		
		panel = new JPanel();
		contentPane.add(panel, "cell 0 0 11 3,grow");
		panel.setLayout(new CardLayout(0, 0));
		
		imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon("C:\\Users\\Sari\\Documents\\Eclipse\\Dante\\spoc.png"));
		panel.add(imageLabel, "name_1382540049281029");
		
		lblPowerRangers = new JLabel("\u01A4\u01A0\u019C\u0404\u01A6 \u01A6\u019B\u019D\u0193\u0404\u01A6\u01A7");
		contentPane.add(lblPowerRangers, "cell 2 4,aligny center");
		
		amountLabel = new JLabel("Amount:");
		contentPane.add(amountLabel, "cell 0 6");
		
		amountText = new JTextField();
		amountText.setColumns(10);
		contentPane.add(amountText, "cell 2 6 4 1,growx");
		
		final generator gen = new generator();
		
		count = new JButton("Generate");
		count.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					try{
						Integer.parseInt(amountText.getText());
						amount = amountText.getText();
					}catch(NumberFormatException e){
						amountText.setText("ONLY NUMBERS!");
					}

			        Calendar cal = Calendar.getInstance();
			        
			        int mm = cal.get(Calendar.MONTH);
			        String month = "0";
			        if (mm < 10) month += mm;
			        else month = "" + mm;
			        int dd = cal.get(Calendar.DAY_OF_MONTH);
			        String day = "0";
			        if (dd < 10) day += dd;
			        else day = "" + dd;
			        String yearS = "" + cal.get(Calendar.YEAR);
			        String year = yearS.substring(2, yearS.length());
					gen.createSerial(month, day, year, amount);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		amountEx = new JLabel("Ex: 1");
		contentPane.add(amountEx, "cell 2 7");
		contentPane.add(count, "cell 0 13");
		
		btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					gen.undoSerial();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnUndo, "cell 2 13");
	}
}
