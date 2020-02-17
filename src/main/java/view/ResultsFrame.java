package view;

import javax.swing.*;
import java.awt.*;

public class ResultsFrame extends JFrame {
    public static JTextArea paper=new JTextArea(30,50);
    public static JScrollPane paperFrame=new JScrollPane(paper);

    Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH=d.width/2,HEIGHT=d.height/2;
    public ResultsFrame() {
        this.setTitle("Simultion results");
        paper.setEditable(false);
        this.setContentPane(paperFrame);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Metoda adauga la sfarsitul unui JTextArea static text,utila
     * cand multe clase scriu in aceasta componenta.
     * @param text
     */
    public static void append(String text){
        paper.append(text);
    }
}
