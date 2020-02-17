package controller;

import model.*;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class ResultsController {
    private SimEnd simGui;
    public ResultsController(SimEnd simGui){
        this.simGui=simGui;
        this.simGui.addSimBtnBehavior(new ShowResults());
    }
    class ShowResults implements ActionListener{//listener pentru butonul care porneste simularea
        public void actionPerformed(ActionEvent e) {
            new ResultsFrame();
            fillStatistics();
        }
    }
    /**
     * Metoda scrie statistici in textArea.
     */
    public void fillStatistics(){
        ResultsFrame.append("\n");
        ResultsFrame.append("-- Global average wait time : "+Statistics.calculateGlobalAvgWait()+" --\n");
        ResultsFrame.append("-- Global average service time : "+Statistics.calculateGlobalAvgServ()+" --\n");
        ResultsFrame.append("-- Global peak time was from : "+Statistics.getGlobalPeakHour(5)+" --\n");
        ResultsFrame.append("-- The total time of inactivity : "+Statistics.getEmptyQueueTime()+" --\n\n");
        Map<CashRegister, ArrayList<Client>> link= Statistics.getLink();
        for(Map.Entry<CashRegister,ArrayList<Client>> entry : link.entrySet()){
            ResultsFrame.append("-- Cash register "+entry.getKey().getName()+" --\n");
            ResultsFrame.append("Average wait time : "+Statistics.calculateAvgWaitPerQueue(entry.getKey())+"\n");
            ResultsFrame.append("Average service time : "+Statistics.calculateAvgServPerQueue(entry.getKey())+"\n");
            ResultsFrame.append("Empty queue time : "+Statistics.emptyPerQueue.get(entry.getKey())+"\n\n");
        }
    }
}
