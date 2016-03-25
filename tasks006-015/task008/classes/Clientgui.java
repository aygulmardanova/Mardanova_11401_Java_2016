package task008.classes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Ekaterina on 20.11.2015.
 */
public class Clientgui extends JFrame {

    private JLabel Name;
    private JButton jButton2;
    private JScrollPane jScrollPane1;
    private JTextField jTextField1;
    private JTextPane jTextPane1;
    final int PORT = 3456;
    StringBuilder story = new StringBuilder();

    public String getHost() {
        return host;
    }

    String host;
    BufferedReader br;
    PrintWriter pw;

    public Clientgui(String host) throws IOException {
        this.host = host;
        initComponents();
        go();
    }

    public void go() throws IOException {
        Socket s = new Socket(host, PORT);
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        pw = new PrintWriter(s.getOutputStream(), true);


        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String string = jTextField1.getText();
                    if (string.matches("[0-9]{4}") && isUnique(string)) {
                        pw.println(string);
                        String line = br.readLine();
                        story.append(string + "\n" + line + "\n");
                        jTextPane1.setText(story.toString());
                        if(line.equals("You win!")){

                            jButton2.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "You win!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
                        }
                        else if (line.equals("You lose!")){
                            jButton2.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "You lose!", "Loser!", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "invalid data format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                jTextField1.setText("");
            }
        });
    }


    public static boolean isUnique(String str) {
        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jButton2 = new JButton();
        jTextField1 = new JTextField();
        Name = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTextPane1 = new JTextPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Your move");

        Name.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        Name.setHorizontalAlignment(SwingConstants.CENTER);
        Name.setText("Bulls and Cows!");

        jScrollPane1.setViewportView(jTextPane1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                                                .addGap(68, 68, 68)
                                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)

                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(Name, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Name, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
                                )
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                .addGap(93, 93, 93))
        );

        Name.getAccessibleContext().setAccessibleName("Bulls and Cows!");

        pack();
        setVisible(true);
    }// </editor-fold>


    public static void main(String args[]) throws IOException {


        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //   Clientgui clientgui = new Clientgui("localhost");

    }

}

