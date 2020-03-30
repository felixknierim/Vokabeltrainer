package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DialogA extends JDialog {
	
	Scanner sca = new Scanner(System.in);
	
	File acc = new File(DialogE.pfad + "/Accurarity.txt");
	static File file = new File(DialogE.pfad + "/Vokabeln.txt");
	
	static String[] [] register;
	static int ctrl = 0;
	float Versuche = 0;
	float Richtig = 0;
	float Accurarity;
	boolean doubleok = false;
	
	static List<String> zähler = new ArrayList<String>();
	static List<String> zähler2 = new ArrayList<String>();
	
	JLabel deu = new JLabel("");
	JLabel Sprache1 = new JLabel("Deutsch");
	JLabel Sprache2 = new JLabel("Fremdsprache");
	JLabel Kontrolle  = new JLabel("");
	JLabel Accu = new JLabel("");
	JLabel Fehler = new JLabel("");
	
	JButton next = new JButton("Nächste Vokabel");
	JButton back = new JButton("Zurück");
	JButton ok = new JButton("Ok");
	
	JTextField eng = new JTextField();
	
	public DialogA() {
		
		if(!DialogE.ordner.exists()) {
			DialogE.ordner.mkdirs();
		}
		if(acc.exists()) {
			Scanner sc;
			try {
					sc = new Scanner(acc);
					 
					 Richtig = sc.nextInt();
					 Versuche = sc.nextInt();
					 
				
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		setTitle("Ausgabe");
		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		Sprache1.setBounds(120, 30, 120, 60);
		Sprache2.setBounds(380, 30, 120, 60);
		deu.setBounds(130, 90, 120, 20);
		eng.setBounds(370, 90, 120, 20);
		next.setBounds(280, 190, 180, 60);
		back.setBounds(255, 280, 90, 60);
		ok.setBounds(170, 190, 90, 60);
		Kontrolle.setBounds(250, 90, 110, 20);
		Accu.setBounds(255, 120, 90, 50);
		Fehler.setBounds(200, 90, 200,60);
		
		back.addActionListener(new ButtonHandler());
		next.addActionListener(new ButtonHandler());
		ok.addActionListener(new ButtonHandler());
		
		add(Sprache1);
		add(Sprache2);
		add(Kontrolle);
		add(back);
		add(Accu);
		
		if (file.exists()) {
			add(ok);
			add(next);
			add(deu);
			add(eng);
			register();
			deu.setText(random());
		} else {
			add(Fehler);
			Fehler.setText("Kein Vokabelverzeichnis gefunden");
		}
	}
	
	public static void register() {
		String pufferstring = "";
		
		try {
			Scanner sc = new Scanner(file);
			
			for(int i = 0;sc.hasNext(); i++) {
				
				pufferstring = sc.nextLine();
				
				
				String[] puffer = pufferstring.split("&&");
				String[] puffer2 = puffer[1].split("!");
				String puffer0 = new String(puffer[0]);
				String puffer1 = new String(puffer2[0]);

				zähler.add(puffer0);
				zähler2.add(puffer1);
			}
			sc.close();
			register = new String[zähler.size()] [2];
			
			for(int i = 0; i < zähler.size(); i++) {
				register[i] [0] = zähler.get(i);
				register[i] [1] = zähler2.get(i);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Datei konnte nicht gefunden werden");
			e.printStackTrace();
		}
		
	}
	
	public static String random() {
		Random rdm = new Random();
		int i = rdm.nextInt(zähler.size());
		if(i == ctrl) {
			return random();
		} else {
			ctrl = i;
			return register[i] [0];
		}
	}
	
	public class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == back) {
					doubleok = false;
					Formatter mat;
					try {
						mat = new Formatter(acc);
						mat.format("%s %s", (int)Richtig, (int)Versuche);
						mat.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				
				
				dispose();
			}
			if(e.getSource() == next) {
				doubleok = false;
				eng.setText(null);
				Kontrolle.setText(null);
				deu.setText(random());
				Accurarity = Richtig/Versuche;
				String Accurarity2 = "" + Accurarity*100;
				Accu.setText(Accurarity2 + "%");;
				
				
			}
			if(e.getSource() == ok) {
				
				if(eng.getText().toUpperCase().equals(register[ctrl] [1].toUpperCase()) && doubleok == false) {
					Kontrolle.setText("Richtig!");
					
					Versuche++;
					Richtig++;
					
					doubleok = true;
				} else if(doubleok == true) {
					
				} else {
					Kontrolle.setText("Falsch!");
					
					Versuche++;
					
				}
				
			}
			
		}
		
		
		
	}
	
	
}
