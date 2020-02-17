package model;

import view.RegisterGrid;
import view.ResultsFrame;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CashRegister implements Runnable{
    private BlockingQueue<Client> queue;//coada propriu zisa
    private AtomicInteger waitingPeriod;//cat ar astepta urmatorul client daca s-ar pune la coada
    private String name;//numele casei de marcat
    private RegisterGrid panel;//reprezentarea in imagini a simularii
    private int time;

    //initializare campuri
    public CashRegister(String name, int size, RegisterGrid panel){
        this.panel=panel;
        this.name=name;
        queue=new ArrayBlockingQueue<Client>(size);
        waitingPeriod=new AtomicInteger();
    }
    public void addClient(Client newClient){
        try {
            queue.put(newClient);//pune clientul in coada
            Statistics.addLink(this, newClient);//fa legatura intre coada si client pentru statistici
            Statistics.addServ(this, newClient.getProcessingPeriod());//inregsitreaza in coada this service time-ul clientului curent
            waitingPeriod.addAndGet(newClient.getProcessingPeriod());//actualizeaza perioada de asteptare pentru coada
            Statistics.addWait(this, waitingPeriod.intValue());//inregistreaza cat au asteptat clientii din coada
            panel.addLabel(Integer.parseInt(this.name)-1,newClient,waitingPeriod.intValue());//aduaga imagine pentru client
            ResultsFrame.append( "Client "+newClient.getID()+" is sitting at cash register "+name+", "+" has arrived at t"+newClient.getArrivalTime()+" and has to process "+newClient.getProcessingPeriod()+" products ." +"The current waititing period was "+waitingPeriod+"\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run() {

        while(true){
            try {
                Client client=queue.take();//sterge capul
                Thread.sleep(client.getProcessingPeriod()*1000);//produsele sunt procesate
                waitingPeriod.addAndGet(-client.getProcessingPeriod());//scade timpul global pe coada
                Statistics.setFinishTime(this, SimulationManager.currentTime,client);//cand paraseste
                panel.removeLabel(Integer.parseInt(this.name)-1,client,waitingPeriod.intValue());//sterge imaginea clientului
                ResultsFrame.append("Client "+client.getID()+" is leaving cash register "+name +" . The current wait period became "+waitingPeriod +"\n");
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }

    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public AtomicInteger getWaitingPeriod(){
        return waitingPeriod;
    }

    public String getName(){
        return name;
    }

}
