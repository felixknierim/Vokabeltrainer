package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Main.GUI.ButtonHandler;

public class DialogE extends JDialog {
	
	JButton back = new JButton("Zurück");
	JButton ok = new JButton("OK");
	JButton path = new JButton("Speicherort");
	
	JLabel deu = new JLabel("Deutsch");
	JLabel eng = new JLabel("Fremdsprache");
	
	JTextField Ideu = new JTextField(9);
	JTextField Ieng = new JTextField(9);
	
	String inputD;
	String inputE;
	String inhalt = "";
	
	
	List<String> Deutsch = new ArrayList();
	List<String> Englisch = new ArrayList();
	
	Formatter matter;
	static String pfad = "C:/Vokabeltrainer/";
	File file = new File(pfad + "/Vokabeln.txt");
	static File ordner = new File(pfad);
	
	public DialogE() {
		
		
		setTitle("Eingabe");
		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		
		ok.setBounds(255, 210, 90, 60);
		back.setBounds(255, 280, 90, 60);
		path.setBounds(345, 280, 90, 60);
		deu.setBounds(90, 30, 120, 60);
		eng.setBounds(430, 30, 120, 60);
		Ideu.setBounds(60, 100, 120, 20);
		Ieng.setBounds(410, 100, 120, 20);
		
		ok.addActionListener(new ButtonHandler2());
		back.addActionListener(new ButtonHandler2());
		path.addActionListener(new ButtonHandler2());
		
		add(ok);
		add(back);
		add(path);
		add(deu);
		add(eng);
		add(Ideu);
		add(Ieng);
	}
	
	public class ButtonHandler2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ok) {
				inputD = Ideu.getText();
				inputE = Ieng.getText();
				if(!(inputD.equals("") || inputD.equals(" ")) && !(inputE.equals("") || inputE.equals(" "))) {
					Deutsch.add(inputD);
					Englisch.add(inputE);
				}
				Ideu.setText(null);
				Ieng.setText(null);
			}
			if(e.getSource() == path) {
				JFileChooser fc = new JFileChooser("");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
				fc.showOpenDialog(null);
				if(fc.getSelectedFile() != null) {
					if(fc.getSelectedFile().getPath() != "") {
						pfad = fc.getSelectedFile().getPath();
					}
				}
				System.out.println(pfad);
			}
			if(e.getSource() == back) {
				try {
					if(!ordner.exists()) {
						ordner.mkdirs();
					}
					if(file.exists()) {
						Scanner sc = new Scanner(file);
						while(sc.hasNext()) {
							inhalt += sc.next();
						}
						sc.close();
						matter = new Formatter(file);
						 String[] line = inhalt.split("!");
						 
						for(int i = 0; i < line.length; i++) {
							line[i] += "!";
							matter.format("%s \n", line[i]);
						}
					} else {
						matter = new Formatter(file);
					}
					for(int i = 0; i < Deutsch.size(); i++) {
						matter.format("%s&&%s!\n", Deutsch.get(i), Englisch.get(i));
					}
					matter.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
			
		}
		
	}

}
