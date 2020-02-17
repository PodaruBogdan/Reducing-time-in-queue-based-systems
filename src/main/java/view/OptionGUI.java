package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionGUI extends JFrame {
    private JLabel arrivalInterval;
    private JLabel serviceInterval;
    private JLabel noQueues;
    private JLabel simInterval;
    private JLabel noClients;
    private JTextField arrivalDataFrom;
    private JTextField arrivalDataTo;
    private JTextField serviceDataFrom;
    private JTextField serviceDataTo;
    private JTextField noQueuesData;
    private JTextField simData;
    private JTextField noClientsData;

    private JButton startBtn;

    public OptionGUI(){
        //preia intervalul de sosire
        arrivalInterval=new JLabel("Arriving interval between customers : ");
        arrivalDataFrom=new JTextField(3);
        JLabel to1=new JLabel(" : ");
        arrivalDataTo=new JTextField(3);
        //panel-ul e organizat pe linii , fiecare linie preia un tip de informatie
        JPanel line1=new JPanel(new FlowLayout(FlowLayout.RIGHT));//zona dedicata din panel pentru interval sosire
        line1.add(arrivalInterval);
        line1.add(arrivalDataFrom);
        line1.add(to1);
        line1.add(arrivalDataTo);
        line1.setBackground(Color.lightGray);
        serviceInterval=new JLabel("Service time interval estimated : ");
        serviceDataFrom=new JTextField(3);
        JLabel to2=new JLabel(" : ");
        serviceDataTo=new JTextField(3);
        JPanel line2=new JPanel(new FlowLayout(FlowLayout.RIGHT));//aliniere la dreapta
        line2.setBackground(Color.lightGray);
        line2.add(serviceInterval);
        line2.add(serviceDataFrom);
        line2.add(to2);
        line2.add(serviceDataTo);
        noQueues=new JLabel("Number of cash regs : ");
        noQueuesData=new JTextField(3);
        JPanel line3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line3.add(noQueues);
        line3.add(noQueuesData);
        line3.setBackground(Color.lightGray);
        JPanel line4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        noClients=new JLabel("Number of clients per reg : ");
        noClientsData=new JTextField(3);
        line4.add(noClients);
        line4.add(noClientsData);
        line4.setBackground(Color.lightGray);
        simInterval=new JLabel("Simulation period (seconds) : ");
        simData=new JTextField(3);
        JPanel line5=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line5.add(simInterval);
        line5.add(simData);
        line5.setBackground(Color.lightGray);

        JPanel line6=new JPanel(new FlowLayout(FlowLayout.CENTER));//buton centrat
        startBtn=new JButton("Start simulation");
        line6.add(startBtn);
        line6.setBackground(Color.lightGray);
        JPanel vertLane=new JPanel();
        vertLane.setLayout(new BoxLayout(vertLane, BoxLayout.PAGE_AXIS));//liniile una sub alta
        vertLane.add(line1);
        vertLane.add(line2);
        vertLane.add(line3);
        vertLane.add(line4);
        vertLane.add(line5);
        vertLane.add(Box.createRigidArea(new Dimension(0,10)));//spatiere
        vertLane.add(line6);
        vertLane.setBackground(Color.lightGray);
        JPanel content=new JPanel(new GridBagLayout());
        content.add(vertLane);
        content.setBackground(Color.lightGray);
        this.setTitle("Settings");
        this.setContentPane(content);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);//centrul ecranului
    }
    public void addStartBtnBehavior(ActionListener listener){
        startBtn.addActionListener(listener);
    }
    //getters
    public JLabel getArrivalInterval() {
        return arrivalInterval;
    }
    public JLabel getServiceInterval() {
        return serviceInterval;
    }
    public JLabel getNoQueues() {
        return noQueues;
    }
    public JLabel getSimInterval() {
        return simInterval;
    }
    public JTextField getArrivalDataFrom() {
        return arrivalDataFrom;
    }
    public JTextField getArrivalDataTo() {
        return arrivalDataTo;
    }
    public JTextField getServiceDataFrom() {
        return serviceDataFrom;
    }
    public JTextField getServiceDataTo() {
        return serviceDataTo;
    }
    public JTextField getNoQueuesData() {
        return noQueuesData;
    }
    public JTextField getSimData() {
        return simData;
    }
    public JTextField getNoClientsData() {
        return noClientsData;
    }
}
