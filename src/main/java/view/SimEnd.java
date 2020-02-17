package view;
import model.Scheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimEnd extends JFrame implements ActionListener{
    private JLabel message;
    private  JButton simBtn;
    public JPanel btnPanel;//continutul se schimba intre buton si gif
    private Scheduler scheduler;
    public SimEnd(Scheduler scheduler) {
        this.scheduler=scheduler;
        message=new JLabel("Simulation has ended !");
        message.setFont(new Font("TimesNewRoman", Font.ITALIC, 25));
        JPanel line1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line1.add(message);
        line1.setBackground(Color.white);
        JPanel line2=new JPanel(new FlowLayout(FlowLayout.CENTER));
        simBtn=new JButton(" View results ");
        simBtn.setVisible(false);
        ImageIcon img=new ImageIcon("giphy.gif");//pune gif prima data
        btnPanel=new JPanel();
        btnPanel.setBackground(Color.white);
        btnPanel.add(new JLabel(img));
        btnPanel.add(simBtn);
        line2.add(btnPanel);
        line2.setBackground(Color.white);
        JPanel vertLane=new JPanel();//2 linii: prima mesaj , a doua buton sau gif
        vertLane.setBackground(Color.white);
        vertLane.setLayout(new BoxLayout(vertLane, BoxLayout.PAGE_AXIS));
        vertLane.add(line1);
        vertLane.add(Box.createRigidArea(new Dimension(0,10)));//spatiere
        vertLane.add(line2);
        JPanel content=new JPanel(new GridBagLayout());
        content.setBackground(Color.white);
        content.add(vertLane);
        this.setBackground(Color.white);
        this.setTitle("Results");
        this.setContentPane(content);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        Timer t=new Timer(500,this);//rata de refresh 0.5s
        t.start();
    }

    public void addSimBtnBehavior(ActionListener listener){
        simBtn.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent e) {
        boolean set=true;
        if(scheduler.getRegs()!=null) {
            for (int i = 0; i < scheduler.getRegs().size(); i++) {
                if (scheduler.getRegs().get(i).getWaitingPeriod().intValue() != 0) {
                    set = false;
                }
            }
            if (set == true) {//toate casele goale=> schimba gif cu buton
                btnPanel.getComponent(0).setVisible(false);
                btnPanel.getComponent(1).setVisible(true);
                this.repaint();
            }
        }
    }
}
