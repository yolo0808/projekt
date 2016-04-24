package projekt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelRozgrywki extends JPanel {

	private JButton stworzTurniej;
	private JButton cofnij;
	private JList<String> turnieje;
	private JScrollPane jScrollPane2;
	   
   public PanelRozgrywki(MenuGlowne menuGlowne) {
       initComponents(menuGlowne);
   }

   private void initComponents(MenuGlowne menuGlowne) {
       java.awt.GridBagConstraints gridBagConstraints;

       
       stworzTurniej = new javax.swing.JButton();
       cofnij = new javax.swing.JButton();
       jScrollPane2 = new javax.swing.JScrollPane();
       turnieje = new javax.swing.JList<>();

       setLayout(new java.awt.GridBagLayout());

       stworzTurniej.setText("Stwórz turniej");
       stworzTurniej.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
           }
       });
       gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.ipadx = 128;
       gridBagConstraints.ipady = 34;
       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
       gridBagConstraints.insets = new java.awt.Insets(20, 38, 19, 0);
       add(stworzTurniej, gridBagConstraints);
       stworzTurniej.getAccessibleContext().setAccessibleName("stworzTurniej");

       cofnij.setText("Cofnij");
       cofnij.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt) {
        	   menuGlowne.setContentPane(menuGlowne.getPanelG());
        	   menuGlowne.revalidate();
           }
       });
       gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = 1;
       gridBagConstraints.gridy = 1;
       gridBagConstraints.gridwidth = 3;
       gridBagConstraints.ipadx = 20;
       gridBagConstraints.ipady = 8;
       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
       gridBagConstraints.insets = new java.awt.Insets(46, 18, 19, 50);
       add(cofnij, gridBagConstraints);

       turnieje.setModel(new javax.swing.AbstractListModel<String>() {
           String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
           public int getSize() { return strings.length; }
           public String getElementAt(int i) { return strings[i]; }
       });
       jScrollPane2.setViewportView(turnieje);

       gridBagConstraints = new java.awt.GridBagConstraints();
       gridBagConstraints.gridx = 0;
       gridBagConstraints.gridy = 0;
       gridBagConstraints.gridwidth = 2;
       gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
       gridBagConstraints.ipadx = 288;
       gridBagConstraints.ipady = 158;
       gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
       gridBagConstraints.weightx = 1.0;
       gridBagConstraints.weighty = 1.0;
       gridBagConstraints.insets = new java.awt.Insets(23, 38, 0, 0);
       add(jScrollPane2, gridBagConstraints);
   }                                                       
}
