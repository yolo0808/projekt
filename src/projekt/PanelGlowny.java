package projekt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelGlowny extends JPanel {
	
	private MenuGlowne menuGlowne;
	
	private JButton przyciskSedziowie;
	private JButton przyciskDruzyny;
	private JButton przyciskRozgrywki;
	
	public PanelGlowny(MenuGlowne menuGlowne){
		setLayout(new GridLayout(3, 1));
		this.menuGlowne = menuGlowne;
		przyciskSedziowie = new JButton("Sêdziowie");
		przyciskDruzyny = new JButton("Dru¿yny");
		przyciskRozgrywki = new JButton("Rozgrywki");
		add(przyciskSedziowie);
		add(przyciskDruzyny);
		add(przyciskRozgrywki);
		
		przyciskSedziowie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuGlowne.setContentPane(menuGlowne.getPanelS());
				menuGlowne.revalidate();
			}
		});
	}

}
