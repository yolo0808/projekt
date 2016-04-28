package projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class PanelStworzTurniej extends JPanel {
	private static final long serialVersionUID = 1L;
	private String[] rodzajeRozgrywek = { "siatkówka", "dwa ognie", "przeci¹ganie liny" };
	private JComboBox<String> listaRozgrywek;
	private JButton cofnij;
	private JButton zatwierdz;
	private JList <Sedzia>listaSedziowie;
	private JList <Druzyna>listaDruzyny; 
	private MenuGlowne menu;
	public PanelStworzTurniej(MenuGlowne menuGlowne) {
		setLayout(new GridLayout(2, 2));
		JPanel panelPrawy = new JPanel(new GridLayout(2, 1));
		
		menu = menuGlowne;
		listaSedziowie = new JList<Sedzia>();
		listaDruzyny = new JList<Druzyna>();
		odswiez();
		zatwierdz = new JButton("zatwierdz");
		zatwierdz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Sedzia> s = new ArrayList<Sedzia>();
				for(Object o : listaSedziowie.getSelectedValues())
					s.add((Sedzia)o);
				ArrayList<Druzyna> d = new ArrayList<Druzyna>();
				for(Object o : listaDruzyny.getSelectedValues()){
					d.add((Druzyna)o);
				}
				
				menuGlowne.getPanelR().getStworzonyT().add(new Turniejo( s,d,(String)listaRozgrywek.getSelectedItem()));
				menuGlowne.getPanelR().odswiez();
			}
		});
		cofnij = new JButton("cofnij");		
		cofnij.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuGlowne.setContentPane(menuGlowne.getPanelR());
				menuGlowne.revalidate();
			}
		});
		
		listaRozgrywek =  new JComboBox<String>(rodzajeRozgrywek);
		
		listaRozgrywek.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listaRozgrywek.getSelectedItem().equals("siatkówka")){
					listaSedziowie.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				}
				else {
					listaSedziowie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				}
				listaSedziowie.clearSelection();
			}
		});
		
		add(listaRozgrywek);
		add(listaSedziowie);
		add(listaDruzyny);
		panelPrawy.add(zatwierdz);
		panelPrawy.add(cofnij);
		add(panelPrawy);
	}
	public void odswiez(){
		Sedzia []sedziowie = new Sedzia [menu.getPanelS().getListaSedziowie2().size()] ;
		Druzyna [] druzyny = new Druzyna [menu.getPanelD().getListaDruzyny2().size()] ;
		sedziowie = menu.getPanelS().getListaSedziowie2().toArray(sedziowie);
		listaSedziowie.setListData(sedziowie);
		druzyny = menu.getPanelD().getListaDruzyny2().toArray(druzyny);
		listaDruzyny.setListData(druzyny);

	}
}
