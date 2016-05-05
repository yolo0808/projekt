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

	String[] tabelaKolumny = {"Dru¿yna",
            "Punkty"};
	String[] meczeKolumny = {"Dru¿yna1",
    "Punkty1","Punkty2", "Dru¿yna2"};
	Object[][] data;
	Object[][] data2;
	JTable table;
	JTable mecze;
	JButton cofnij;
	JButton zatwierdz;
	
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
				przeliczPunkty();
			}
		});
		
		data = new Object[turniejo.getListaDruzynyT().size()][2];
		int i = 0;
		for(String druzyna : turniejo.getTabela().keySet()){
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
		
		add(scrollPane);
		add(scrollPane2);
		//
		JPanel panelPrawy = new JPanel(new GridLayout(2, 1));
		panelPrawy.add(zatwierdz);
		panelPrawy.add(cofnij);
		add(panelPrawy);
		
		przeliczPunkty();
	}
	
	private void przeliczPunkty(){
		TableModel model = mecze.getModel();
		for(int i = 0; i<mecze.getRowCount(); i++){
			String wynik1s = (String)model.getValueAt(i, 1);
			String wynik2s = (String)model.getValueAt(i, 2);
			if(wynik1s.equals(null) || wynik2s.equals(null))
				continue;
			
			turniejo.setWynik((String)model.getValueAt(i, 0), (String)model.getValueAt(i, 1), (String)model.getValueAt(i, 2), (String)model.getValueAt(i, 3));
		}
		
		turniejo.generujTabele();
		
		TableModel modelTabela = table.getModel();
		String[][] tabela = turniejo.getPosortowanaTabela();
		for(int i=0; i<tabela.length; i++){
			modelTabela.setValueAt(tabela[i][0], i, 0);
			modelTabela.setValueAt(tabela[i][1], i, 1);
		}
		table.setModel(modelTabela);
		
		menuGlowne.getPanelR().zapis();
	}
	
}
