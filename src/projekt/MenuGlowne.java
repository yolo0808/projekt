package projekt;

import javax.swing.JFrame;

public class MenuGlowne extends JFrame {
	private PanelGlowny panelG;
	private PanelSedziowie panelS;
	public static void main(String[] args){
		new MenuGlowne();
	}
	
	public MenuGlowne() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		panelG = new PanelGlowny(this);
		panelS = new PanelSedziowie(this);
		
		setContentPane(panelG);
		revalidate();
		
	}

	public PanelGlowny getPanelG() {
		return panelG;
	}

	public PanelSedziowie getPanelS() {
		return panelS;
	}
	
}
