package projekt;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
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

       
       stworzTurniej = new JButton();
       cofnij = new JButton();
       jScrollPane2 = new JScrollPane();
       turnieje = new JList<>();

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

       turnieje.setModel(new AbstractListModel<String>() {
           String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
           public int getSize() { return strings.length; }
           public String getElementAt(int i) { return strings[i]; }
       });
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
   }                                                       
}
