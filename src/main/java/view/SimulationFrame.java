package view;

import model.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame implements ActionListener {
    private static final long serialVerionUID=1L;
    private JPanel panel;
    Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH=d.width,HEIGHT=d.height;
    private JPanel tick;
    private JLabel time;
    private JPanel backPane;
    public SimulationFrame(JPanel panel){
        time=new JLabel("GLOBAL TIME");
        time.setFont(new Font("TimesNewRoman", Font.ITALIC, 25));
        tick=new JPanel();
        tick.setMaximumSize(new Dimension(WIDTH,25));
        tick.add(time);
        backPane=new JPanel();
        backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
        backPane.add(tick);//panou cu ceas
        backPane.add(panel);//panou de animatie

        this.panel=panel;
        Timer t=new Timer(500,this);//rata de refresh 0.5s
        t.start();
        this.setContentPane(backPane);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {//actualizeaza timp global
        time.setText("GLOBAL TIME :: "+SimulationManager.currentTime);
        this.setContentPane(backPane);
    }
}
