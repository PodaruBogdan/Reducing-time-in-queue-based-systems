package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController {
    private OptionGUI optionGui;
    public SimulationController(OptionGUI opGui){
        optionGui=opGui;//fereastra cu informatiile de completat
        optionGui.addStartBtnBehavior(new ShowSimulation());
    }
    class ShowSimulation implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int noQ;
            noQ=Integer.parseInt(optionGui.getNoQueuesData().getText());//preia numar case marcat
            RegisterGrid regGrid=new RegisterGrid(noQ);
            new SimulationFrame(regGrid);//pregateste animatia(pune noQ poze cu case de marcat)
            SimulationManager gen=new SimulationManager(optionGui,regGrid);
            Thread t=new Thread(gen);//porneste thread care se ocupa de casele de marcat
            t.start();

        }
    }
}
