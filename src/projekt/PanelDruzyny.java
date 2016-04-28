package projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class PanelDruzyny extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField poleDruzyny;
	private JButton dodaj;
	private JButton usun;
	private JButton cofnij;
	private Druzyna[] druzyny;
	private JList<Druzyna> listaDruzyny;
	private ArrayList<Druzyna> listaDruzyny2;
	public PanelDruzyny(MenuGlowne menuGlowne){
		super();
		setLayout(new GridLayout(1, 2));
		listaDruzyny = new JList<Druzyna>();
		listaDruzyny.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(listaDruzyny);
		JPanel panelPrawy = new JPanel(new GridLayout(4, 1));
		add(panelPrawy);
		dodaj = new JButton("Dodaj dru¿ynê");
		usun = new JButton("Usuñ druzynê");
		cofnij = new JButton("Wróæ do Menu");
		poleDruzyny = new JTextField();
		poleDruzyny.setToolTipText("nazwa druzyny");
		panelPrawy.add(poleDruzyny);
		panelPrawy.add(dodaj);
		panelPrawy.add(usun);
		panelPrawy.add(cofnij);
		
		cofnij.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuGlowne.setContentPane(menuGlowne.getPanelG());
				menuGlowne.revalidate();
			}
		});

		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dodajDruzyne(poleDruzyny.getText());
			}
		});

		usun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usunDruzyne();
			}
		});
		
		wczytajDruzyny();
		druzyny = new Druzyna[listaDruzyny2.size()];
		ustawDruzyny();
	}
	
	private void wczytajDruzyny() {
		Scanner scr;
		try {
			scr = new Scanner(new File("druzyny"));
			String druzyny = scr.nextLine();
			String[] splitDruzyny = druzyny.split(";");
				listaDruzyny2 = new ArrayList<Druzyna>();
			for (String druzyna : splitDruzyny) {
				listaDruzyny2.add(new Druzyna(druzyna));
			}
			scr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dodajDruzyne(String nazwa) {
		listaDruzyny2.add(new Druzyna(nazwa));
		ustawDruzyny();
		zapis();

	}

	private void ustawDruzyny() {
		druzyny = listaDruzyny2.toArray(druzyny);
		listaDruzyny.setListData(druzyny);
	}

	private void usunDruzyne() {
		int indeks;
		indeks = listaDruzyny.getSelectedIndex();
		listaDruzyny2.remove(indeks);
		ustawDruzyny();
		zapis();
	}

	private void zapis() {
		PrintWriter zapis;
		try {
			zapis = new PrintWriter("druzyny");
			for (Druzyna druzyna : listaDruzyny2) {
				zapis.print(druzyna);
				zapis.print(";");
			}

			zapis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Druzyna> getListaDruzyny2() {
		return listaDruzyny2;
	}
	
}
