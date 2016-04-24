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

public class PanelSedziowie extends JPanel {
	private ArrayList<Sedzia> listaSedziowie2;
	private Sedzia[] sedziowie;
	private JList<Sedzia> listaSedziowie;
	private static final long serialVersionUID = 1L;
	private JTextField poleImie;
	private JTextField poleNazwisko;
	private JButton dodaj;
	private JButton usun;
	private JButton cofnij;

	public PanelSedziowie(MenuGlowne menuGlowne) {
		super();
		setLayout(new GridLayout(1, 2));
		listaSedziowie = new JList<Sedzia>();
		listaSedziowie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(listaSedziowie);
		JPanel panelPrawy = new JPanel(new GridLayout(5, 1));
		add(panelPrawy);
		dodaj = new JButton("Dodaj sêdziego");
		usun = new JButton("Usuñ sêdziego");
		cofnij = new JButton("Wróæ do Menu");
		poleImie = new JTextField();
		poleImie.setToolTipText("imiê");
		poleNazwisko = new JTextField();
		poleNazwisko.setToolTipText("nazwisko");
		panelPrawy.add(poleImie);
		panelPrawy.add(poleNazwisko);
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

		dodaj.addActionListener(new ActionListener() { // klasa anonimowa

			@Override
			public void actionPerformed(ActionEvent e) {
				dodajSedziego(poleImie.getText(), poleNazwisko.getText());

			}
		});

		usun.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usunSedziow();

			}
		});

		wczytajSedziow();
		sedziowie = new Sedzia[listaSedziowie2.size()];
		ustawSedziow();
	}

	private void wczytajSedziow() {
		Scanner scr;
		try {
			scr = new Scanner(new File("sedziowie"));
			String sedziowie = scr.nextLine();
			String[] splitSedziowie = sedziowie.split(";");
			listaSedziowie2 = new ArrayList<Sedzia>();
			for (String sedzia : splitSedziowie) {
				listaSedziowie2.add(new Sedzia(sedzia));
			}
			scr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dodajSedziego(String imie, String nazwisko) {
		listaSedziowie2.add(new Sedzia(imie, nazwisko));
		ustawSedziow();
		zapis();

	}

	private void ustawSedziow() {
		sedziowie = listaSedziowie2.toArray(sedziowie);
		listaSedziowie.setListData(sedziowie);
	}

	private void usunSedziow() {
		int indeks;
		indeks = listaSedziowie.getSelectedIndex();
		listaSedziowie2.remove(indeks);
		ustawSedziow();
		zapis();
	}

	private void zapis() {
		PrintWriter zapis;
		try {
			zapis = new PrintWriter("sedziowie");
			for (Sedzia sedzia : listaSedziowie2) {
				zapis.print(sedzia);
				zapis.print(";");
			}

			zapis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
