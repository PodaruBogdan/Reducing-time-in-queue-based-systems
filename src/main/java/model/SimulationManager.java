package model;
import controller.ResultsController;
import controller.SimulationController;
import view.*;
import java.util.*;

public class SimulationManager implements Runnable{
    public int timeLimit=100;//cat dureaza simularea
    public int maxProcTime=10;//durata de procesare a unui client nu depaseste aceasta valoare
    public int minProcTime=2;//minimul duratei de procesare
    public int minArriveTime;//clientul soseste intr-un timp mai mult de
    public int maxArriveTime;// clientul soseste intr-un timp mai putin de
    public int numberOfRegs=3;//numarul de case de marcat
    public int numberOfClients=100;//numarul de clienti/casa de marcat
    public SelectionPolicy selectionPolicy=SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Client> generatedClients;
    public static int currentTime=0;
    private OptionGUI optionPanel;
    public void run() {
        while(currentTime<timeLimit){
            for(int i=0;i<generatedClients.size();i++) {
                if ( generatedClients.get(i).getArrivalTime() == currentTime) {
                    scheduler.dispatchClient(generatedClients.get(i));//aduaga la o coada
                    generatedClients.remove(generatedClients.get(i));//sterge din lista , nu il mai adauga alte cozi
                }

            }
            for(int i=0;i<scheduler.getRegs().size();i++){//cauta cozile goale
                if(scheduler.getRegs().get(i).getWaitingPeriod().intValue()==0){
                    Statistics.incEmptyTimePerQueue(scheduler.getRegs().get(i));
                }
            }
            currentTime++;
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("can't wait");
                }
            }
        new ResultsController(new SimEnd(scheduler));
    }
    //intializare valori
    public SimulationManager(OptionGUI optionPanel, RegisterGrid panel){
        this.optionPanel=optionPanel;
        //preia date din GUI-ul de settings
        timeLimit=Integer.parseInt(optionPanel.getSimData().getText());
        minProcTime = Integer.parseInt(optionPanel.getServiceDataFrom().getText());
        maxProcTime=Integer.parseInt(optionPanel.getServiceDataTo().getText());
        minArriveTime=Integer.parseInt(optionPanel.getArrivalDataFrom().getText());
        maxArriveTime=Integer.parseInt(optionPanel.getArrivalDataTo().getText());
        numberOfRegs=Integer.parseInt(optionPanel.getNoQueuesData().getText());
        numberOfClients=Integer.parseInt(optionPanel.getNoClientsData().getText());
        generatedClients=new LinkedList<Client>();
        Statistics.setNrQueues(numberOfRegs);
        Statistics.setNrClientsGenerated(numberOfClients);
        Statistics.setSimInterval(timeLimit);
        //scrie datele simularii
        ResultsFrame.append("-- Time the store was open : "+timeLimit+" --\n");
        ResultsFrame.append("-- Clients were coming to cash register between "+minArriveTime+" and "+maxArriveTime+" --\n");
        ResultsFrame.append("-- The process time for each client was somewhere between "+minProcTime+" and "+maxProcTime+" --\n");
        ResultsFrame.append(" -- Number of cash registers opened was "+numberOfRegs+" --\n");
        ResultsFrame.append(" -- Number of clients/register never exceed "+numberOfClients+" --\n\n");
        ResultsFrame.append("-- History -- \n");
        scheduler=new Scheduler(numberOfRegs,numberOfClients,panel);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomClients();
        new SimulationController(optionPanel);
    }
    private void generateNRandomClients(){
        int global=0;
        for(int i=0;i<numberOfClients*numberOfRegs;i++){
            int arrInt=(maxArriveTime-minArriveTime);//interval de sosire
            int procInt=(maxProcTime-minProcTime);//interval de procesare
            int arr=((int)(Math.random()*10))%arrInt+minArriveTime;//numar random in interval sosire
            int pro=((int)(Math.random()*10))%procInt+minProcTime;//numar random in interval procesare
            global+=arr;//timpi diferiti de sosire
            Client c=new Client((i+1),global,pro);
            generatedClients.add(c);
        }
        Collections.sort(generatedClients);
    }
}
