package com.zetcode;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Character extends JDialog {
   private JPanel contentPane = new JPanel(); // ÆÐ³Î °´Ã¼È­
   private JLabel text = new JLabel("<html><center>Choose your character<br/>to play as!</center></html>",
         SwingConstants.CENTER);
   @SuppressWarnings("rawtypes")
   public JComboBox santaList;
   public JButton okButton = new JButton("OK");

   private ImageIcon[] images;
   private String[] Santa = { "Red", "Blue", "Purple", "Orange", "Green" };

   private String[] santa_resources = { "Red", "Blue", "Purple", "Orange", "Green" };

   private int selectedIndex;
   public String selected;

   @SuppressWarnings({ "unchecked", "rawtypes", "unused", "deprecation" }) // "deprecation" })

   public Character(String selected) {
      super();
      setModal(true);
      setTitle("Choose Charcter");

      this.selected = selected;

      images = new ImageIcon[santa_resources.length];
      Integer[] intArray = new Integer[santa_resources.length];
      int i = 0;

      for (String player : santa_resources) {
         intArray[i] = new Integer(i);
         images[i] = new ImageIcon("src/resources/santa/" + santa_resources[i] + ".png");

         if (santa_resources[i].equals(this.selected)) {
            selectedIndex = i;
         }
         i++;
      }

      santaList = new JComboBox(intArray);
      ComboBoxRenderer renderer = new ComboBoxRenderer();
      renderer.setPreferredSize(new Dimension(120, 32));
      santaList.setRenderer(renderer);

      contentPane.setLayout(null);

      contentPane.add(text);
      text.setLocation(16, 16);
      text.setSize(288, 32);
      text.setFont(new Font("Courier New", Font.BOLD, 16));

      contentPane.add(santaList);
      santaList.setLocation(16, 80);
      santaList.setSize(288, 40);
      santaList.setFont(new Font("Courier New", Font.BOLD, 16));
      santaList.setMaximumRowCount(10);
      santaList.setSelectedIndex(selectedIndex);

      contentPane.add(okButton);
      okButton.setLocation(16, 144);
      okButton.setSize(288, 32);
      okButton.setFont(new Font("Courier New", Font.BOLD, 16));

      add(contentPane);
      contentPane.setPreferredSize(new Dimension(320, 192));

      setResizable(false);
      pack();

      setLocationRelativeTo(null);
      setDefaultCloseOperation(HIDE_ON_CLOSE);
   }

   @SuppressWarnings({ "rawtypes" })
   class ComboBoxRenderer extends JLabel implements ListCellRenderer {
      public ComboBoxRenderer() {
         setOpaque(true);
         setHorizontalAlignment(CENTER);
         setVerticalAlignment(CENTER);
         selected = santa_resources[selectedIndex];
      }
      @Override
      public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
         int selectedIndex = ((Integer) value).intValue();

         if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
            selected = santa_resources[selectedIndex];
         } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
         }
         setIcon(images[selectedIndex]);
         setText(Santa[selectedIndex]);
         setFont(list.getFont());

         return this;
      }
   }
}