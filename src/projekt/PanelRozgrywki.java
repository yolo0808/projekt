package projekt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelRozgrywki extends JPanel {

	private JButton stworzTurniej;
	private JButton cofnij;
	private JList<Turniejo> turnieje;
	private JScrollPane jScrollPane2;
	private ArrayList<Turniejo> stworzonyT;   
	public PanelRozgrywki(MenuGlowne menuGlowne) {
       initComponents(menuGlowne);
       
   }

   private void initComponents(MenuGlowne menuGlowne) {
       java.awt.GridBagConstraints gridBagConstraints;

       stworzonyT = new ArrayList<Turniejo>();
       stworzTurniej = new JButton();
       cofnij = new JButton();
       jScrollPane2 = new JScrollPane();
       turnieje = new JList<Turniejo>();

       setLayout(new GridBagLayout());

       stworzTurniej.setText("Stwórz turniej");
       stworzTurniej.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   
           }
       });
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.ipadx = 128;
       gridBagConstraints.ipady = 34;
       gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
       gridBagConstraints.insets = new Insets(20, 38, 19, 0);
       add(stworzTurniej, gridBagConstraints);
       stworzTurniej.getAccessibleContext().setAccessibleName("stworzTurniej");

       cofnij.setText("Cofnij");
       stworzTurniej.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			menuGlowne.getPanelST().odswiez();
			menuGlowne.setContentPane(menuGlowne.getPanelST());
			menuGlowne.revalidate();
		}
	});
       cofnij.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   menuGlowne.setContentPane(menuGlowne.getPanelG());
        	   menuGlowne.revalidate();
           }
       });
       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.gridwidth = 3;
       gridBagConstraints.ipadx = 20;
       gridBagConstraints.ipady = 8;
       gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
       gridBagConstraints.insets = new Insets(46, 18, 19, 50);
       add(cofnij, gridBagConstraints);

      
       jScrollPane2.setViewportView(turnieje);

       gridBagConstraints = new GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 0;
       gridBagConstraints.gridwidth = 2;
       gridBagConstraints.fill = GridBagConstraints.BOTH;
       gridBagConstraints.ipadx = 288;
       gridBagConstraints.ipady = 158;
       gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
       gridBagConstraints.weightx = 1.0;
       gridBagConstraints.weighty = 1.0;
       gridBagConstraints.insets = new Insets(23, 38, 0, 0);
       add(jScrollPane2, gridBagConstraints);
       
       turnieje.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			menuGlowne.setContentPane(new PanelTurniejow(menuGlowne, turnieje.getSelectedValue()));
			menuGlowne.revalidate();
			//System.out.println(turnieje.getSelectedIndex());
		}
	});
       
       
       wczytajTurnieje();
       odswiez();
   }

   		public ArrayList<Turniejo> getStworzonyT() {
   			return stworzonyT;
}  	                                                     
   public void odswiez(){
	   Turniejo [] turniej = new Turniejo [stworzonyT.size()] ;
		turniej = stworzonyT.toArray(turniej);
		turnieje.setListData(turniej);
		zapis();
   }
   
	private void wczytajTurnieje() {
		Scanner scr;
		try {
			scr = new Scanner(new File("turnieje2"));
			if(!scr.hasNextLine()){
				scr.close();
				return;
			}
			String t = scr.nextLine();
			System.out.println(t);
			String[] splitT = t.split(">");
			stworzonyT = new ArrayList<Turniejo>();
			for (String turniej : splitT) {
				System.out.println(turniej);
				String[] split2T = turniej.split("/");			
				stworzonyT.add(new Turniejo(split2T[0], split2T[1], split2T[2], split2T[3], split2T[4]));
			}
			scr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
	public void zapis() {
		PrintWriter zapis;
		try {
			zapis = new PrintWriter("turnieje2");
			for (Turniejo turniej : stworzonyT) {
				zapis.print(turniej.getRodzaj());
				zapis.print("/");
				zapis.print(turniej.getDataUtworzenia());
				zapis.print("/");
				for(Druzyna d : turniej.getListaDruzynyT()){
					zapis.print(d);
					zapis.print(";");
				}
				zapis.print("/");
				for(Sedzia s : turniej.getListaSedziowieT()){
					zapis.print(s);
					zapis.print(";");
				}
				zapis.print("/");
				String [][] mecze = turniej.getMecze();
				for(int i=0; i<mecze.length; i++){
					zapis.print(mecze[i][0]);
					zapis.print(";");
					zapis.print(mecze[i][1]);
					zapis.print(";");
					zapis.print(mecze[i][2]);
					zapis.print(";");
					zapis.print(mecze[i][3]);
					zapis.print("<");
				}
				
				String [][] finaly = turniej.getFinaly();
				for(int i=0; i<finaly.length; i++){
					zapis.print(finaly[i][0]);
					zapis.print(";");
					zapis.print(finaly[i][1]);
					zapis.print(";");
					zapis.print(finaly[i][2]);
					zapis.print(";");
					zapis.print(finaly[i][3]);
					zapis.print("<");
				}
				
				zapis.print(">");
			}

			zapis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
