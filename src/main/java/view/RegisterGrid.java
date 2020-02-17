package view;
import model.Client;
import javax.swing.*;
import java.awt.*;

public class RegisterGrid extends JPanel {
    private JPanel[] panels;
    private JLabel[] labels;
    private int lines;
    public RegisterGrid(int lines){
        this.lines=lines;//cate case de marcat
        ImageIcon img=new ImageIcon("cash_manager.png");//imagine casier
        panels=new JPanel[lines];//puse dinamic
        labels=new JLabel[lines];
        this.setLayout(new GridLayout(lines,1));//casele una sub alta
        for(int i=0;i<lines;i++){
            labels[i]=new JLabel("wait: ");//eticheta timp astepare
            JPanel jp=new JPanel();
            jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));//eticheta sub imagine
            panels[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel j=new JLabel(img);
            jp.add(j);
            jp.add(labels[i]);
            panels[i].add(jp);//datele salvate in panels pentru a fi accesate de alte clase
            this.add(panels[i]);
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }//actualizare

    /**
     * Corespondenta 1:1 intre client adaugat intr-o coada cu o imagine
     * adaugata in panoul de afisare.
     * @param i
     * @param c
     * @param waitPer
     */
    public void addLabel(int i,Client c,int waitPer){
        int rand=(int)(Math.random()*10);
        JPanel jp=new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        ImageIcon img=new ImageIcon("man_hold_small.png");
        ImageIcon img2=new ImageIcon("buyer_push.png");
        JLabel j;
        if(rand%2==0) {//pune una din imagini pentru clienti
             j= new JLabel(img);
        }
        else
        {
            j=new JLabel(img2);
        }
        jp.add(j);
        jp.add(new JLabel("proc: "+c.getProcessingPeriod()));
        panels[i].add(jp);//pune la stanga o imagine noua pentru un client nou
        panels[i].repaint();
        labels[i].setText("wait: "+waitPer);
        this.repaint();
    }

    /**
     * Sterge imaginea asociata unui client la expirarea timpului de procesare.
     * @param i
     * @param c
     * @param waitPer
     */
    public void removeLabel(int i, Client c,int waitPer){
        if(panels[i].getComponentCount()>1) {
            panels[i].remove(1);//sterge cand se termina timpul de proc si imaginea
            labels[i].setText("wait: "+waitPer);
        }
        panels[i].repaint();
        this.repaint();
    }
}
