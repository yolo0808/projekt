package projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class PanelTurniejow extends JPanel {

	String[] tabelaKolumny = { "Dru¿yna", "Punkty" };
	String[] meczeKolumny = { "Dru¿yna1", "Punkty1", "Punkty2", "Dru¿yna2" };
	Object[][] data;
	Object[][] data2;
	Object[][] dataFinaly;
	JTable table;
	JTable mecze;
	JTable finaly;
	JButton cofnij;
	JButton zatwierdz;
	JButton zatwierdzPolfinal;
	JButton zatwierdzFinal;

	Turniejo turniejo;
	MenuGlowne menuGlowne;

	public PanelTurniejow(MenuGlowne menuGlowne, Turniejo turniejo) {
		this.turniejo = turniejo;
		this.menuGlowne = menuGlowne;

		setLayout(new GridLayout(2, 2));
		cofnij = new JButton("cofnij");
		cofnij.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuGlowne.setContentPane(menuGlowne.getPanelR());
				menuGlowne.revalidate();
			}
		});

		zatwierdz = new JButton("zatwierdz");
		zatwierdz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				przeliczPunkty(false);
			}
		});

		zatwierdzPolfinal = new JButton("zatwierdzPolfinal");
		zatwierdzPolfinal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				przeliczPolfinaly();
			}
		});

		zatwierdzFinal = new JButton("zatwierdzFinal");
		zatwierdzFinal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zapiszFinal();
			}
		});

		data = new Object[turniejo.getListaDruzynyT().size()][2];
		int i = 0;
		for (String druzyna : turniejo.getTabela().keySet()) {
			data[i][0] = druzyna;
			data[i][1] = turniejo.getTabela().get(druzyna);
			i++;
		}
		table = new JTable(data, tabelaKolumny);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);

		data2 = turniejo.getMecze();
		mecze = new JTable(data2, meczeKolumny);
		JScrollPane scrollPane2 = new JScrollPane(mecze);
		mecze.setFillsViewportHeight(true);

		dataFinaly = turniejo.getFinaly();
		finaly = new JTable(dataFinaly, meczeKolumny);
		JScrollPane scrollPane3 = new JScrollPane(finaly);
		finaly.setFillsViewportHeight(true);

		add(scrollPane);
		add(scrollPane2);
		add(scrollPane3);
		//
		JPanel panelPrawy = new JPanel(new GridLayout(2, 1));
		JPanel przyciski = new JPanel(new GridLayout(1, 3));
		przyciski.add(zatwierdz);
		przyciski.add(zatwierdzPolfinal);
		przyciski.add(zatwierdzFinal);
		panelPrawy.add(przyciski);
		panelPrawy.add(cofnij);
		add(panelPrawy);

		przeliczPunkty(true);
	}

	private void przeliczPunkty(boolean first) {
		TableModel model = mecze.getModel();
		boolean wszystkie = true;
		for (int i = 0; i < mecze.getRowCount(); i++) {
			String wynik1s = (String) model.getValueAt(i, 1);
			String wynik2s = (String) model.getValueAt(i, 2);
			if (wynik1s.equals(null) || wynik2s.equals(null)) {
				wszystkie = false;
				continue;
			}

			turniejo.setWynik((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1),
					(String) model.getValueAt(i, 2), (String) model.getValueAt(i, 3));
		}

		turniejo.generujTabele();

		if (wszystkie && !first) {
			generujPolfinaly();
		}

		TableModel modelTabela = table.getModel();
		String[][] tabela = turniejo.getPosortowanaTabela();
		for (int i = 0; i < tabela.length; i++) {
			modelTabela.setValueAt(tabela[i][0], i, 0);
			modelTabela.setValueAt(tabela[i][1], i, 1);
		}
		table.setModel(modelTabela);

		menuGlowne.getPanelR().zapis();
	}

	private void generujPolfinaly() {
		turniejo.generujPolfinaly();

		TableModel modelTabela = finaly.getModel();
		String[][] tabela = turniejo.getFinaly();
		for (int i = 0; i < tabela.length; i++) {
			modelTabela.setValueAt(tabela[i][0], i, 0);
			modelTabela.setValueAt(tabela[i][3], i, 3);
		}
		finaly.setModel(modelTabela);
		
	}

	private void przeliczPolfinaly() {
		int j = 0;
		TableModel model = finaly.getModel();
		for (int i = 0; i < finaly.getRowCount(); i++) {
			String wynik1s = (String) model.getValueAt(i, 1);
			String wynik2s = (String) model.getValueAt(i, 2);
			System.out.println(wynik1s + " " + wynik2s);
			if (wynik1s == null || wynik2s == null || wynik1s.equals(null) || wynik2s.equals(null)) {
				continue;
			}
			j++;

			turniejo.setWynikFinalow((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1),
					(String) model.getValueAt(i, 2), (String) model.getValueAt(i, 3));
		}
		if (j > 1) {
			turniejo.generujFinal();
			TableModel modelTabela = finaly.getModel();
			String[][] tabela = turniejo.getFinaly();
			modelTabela.setValueAt(tabela[2][0], 2, 0);
			modelTabela.setValueAt(tabela[2][3], 2, 3);
			finaly.setModel(modelTabela);
		}
		
		menuGlowne.getPanelR().zapis();
	}

	private void zapiszFinal() {
		TableModel model = finaly.getModel();
		String wynik1s = (String) model.getValueAt(2, 1);
		String wynik2s = (String) model.getValueAt(2, 2);
		if (wynik1s == null || wynik2s == null || wynik1s.equals(null) || wynik2s.equals(null)) {
			return;
		}

		turniejo.setWynikFinalow((String) model.getValueAt(2, 0), (String) model.getValueAt(2, 1),
				(String) model.getValueAt(2, 2), (String) model.getValueAt(2, 3));
		
		menuGlowne.getPanelR().zapis();
	}

}
