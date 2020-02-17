package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Comparable<Client>{

    private int ID;//id unic
    private int arrivalTime;//cand intra in coada
    private int finishTime;//cand iese din coada
    private int processingPeriod;//cat are de procesat
    private int waitingPeriodOnChosenRegister;

    public Client(int ID,int arrTime,int procTime){
        this.ID=ID;
        arrivalTime=arrTime;
        processingPeriod=procTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }

    public int getWaitingPeriodOnChosenRegister() {
        return waitingPeriodOnChosenRegister;
    }

    public void setWaitingPeriodOnChosenRegister(int waitingPeriodOnChosenRegister) {
        this.waitingPeriodOnChosenRegister = waitingPeriodOnChosenRegister;
    }

    public int compareTo(Client o) {
         if(arrivalTime<o.arrivalTime){
             return -1;
         }
         else if(arrivalTime == o.arrivalTime){
             return 0;
         }else{
             return 1;
         }
    }
    public int getID(){
        return ID;
    }
}
