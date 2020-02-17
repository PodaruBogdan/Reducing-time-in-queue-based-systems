package model;

import controller.SimulationController;
import view.OptionGUI;


public class MainClass {
    public static void main(String[] args){
        new SimulationController(new OptionGUI());
    }
}
