package projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelSedziowie extends JPanel {
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
		add(listaSedziowie);
		JPanel panelPrawy = new JPanel(new GridLayout(5, 1));
		add(panelPrawy);
		dodaj = new JButton("Dodaj sêdziego");
		usun = new JButton("Usuñ sêdziego");
		cofnij = new JButton("Wróæ do Menu");
		poleImie = new JTextField();
		poleNazwisko = new JTextField();
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
	}
	
	
	
}
