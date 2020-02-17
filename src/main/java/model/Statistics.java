package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Statistics {
    private static int nrClientsGenerated;
    private static int nrQueues;
    private static int simInterval;


    private static Map<CashRegister, ArrayList<Client>> link=new HashMap<CashRegister, ArrayList<Client>>();
    private static Map<CashRegister,Integer> waitPerQueue=new HashMap<CashRegister, Integer>();
    private static Map<CashRegister,Integer> servPerQueue=new HashMap<CashRegister, Integer>();
    private static Map<CashRegister,Integer> clientsInIntervalPerQueue=new HashMap<CashRegister, Integer>();
    public static Map<CashRegister,Integer> emptyPerQueue=new HashMap<CashRegister, Integer>();

    private static int emptyTime;
    public static int countEmpty;
    /**
     * Calculeaza media timpului de astepare pentru un client din magazin.
     * @return int
     */
    public static int calculateGlobalAvgWait(){
        int sum=0;
        for(CashRegister cr:link.keySet()){
            sum+=calculateAvgWaitPerQueue(cr);//suma mediilor de asteptare pe fiecare casa
        }
        if(sum!=0){
            return sum/nrQueues;
        }
        return 0;
    }
    /**
     * Calculeaza media timpului de servire pentru un client din magazin.
     * @return int
     */
    public static int calculateGlobalAvgServ(){
        int sum=0;
        for(CashRegister cr:link.keySet()){
            sum+=calculateAvgServPerQueue(cr);//suma mediilor de servire pe fiecare casa
        }
        if(sum!=0){
            return sum/nrQueues;
        }
        return 0;
    }

    /**
     * Face legatura intre o casa de marcat si un client.
     * @param cr
     * @param c
     */
    public static void addLink(CashRegister cr,Client c){
        ArrayList<Client> vals=new ArrayList<Client>();
        if(link.get(cr)==null)
            link.put(cr,vals);
        link.get(cr).add(c);
        increaseClients(cr);
    }

    /**
     * Adauaga timp de asteptare pentru o coada.
     * @param cr
     * @param waitPer
     */
    public static void addWait(CashRegister cr,int waitPer){
        if(waitPerQueue.get(cr)!=null) {
            int old = waitPerQueue.get(cr);
            old+=waitPer;
            waitPerQueue.put(cr, old);
        }
        else
        {
            waitPerQueue.put(cr, waitPer);
        }
    }

    /**
     * Calculeaza timpul de astepare mediu pentru o coada.
     * @param cr
     * @return int
     */
    public static int calculateAvgWaitPerQueue(CashRegister cr){
        if(waitPerQueue.get(cr)!=null && clientsInIntervalPerQueue.get(cr)!=null) {
             int total= waitPerQueue.get(cr);
            return (total / clientsInIntervalPerQueue.get(cr));
        }
        return -1;
    }

    /**
     * Cati clienti au fost la o coada .
     * @param cr
     */
    public static void increaseClients(CashRegister cr){

        if(clientsInIntervalPerQueue.get(cr)!=null) {
            int nr = clientsInIntervalPerQueue.get(cr);
            clientsInIntervalPerQueue.put(cr, ++nr);
        }
        else
            clientsInIntervalPerQueue.put(cr, 1);
    }

    /**
     * Seteaza timpul de finalizare pentru un client dintr-o coada.
     * @param cr
     * @param finishTime
     */
    public static void setFinishTime(CashRegister cr,int finishTime,Client client){
        if(link.get(cr)!=null) {
            Client c = link.get(cr).get(link.get(cr).indexOf(client));
            c.setFinishTime(finishTime);
        }
    }/**
     * Adauaga timp de servire pentru o coada.
     * @param cr
     * @param servPer
     */
    public static void addServ(CashRegister cr,int servPer){
        if(servPerQueue.get(cr)!=null ) {
            int old = servPerQueue.get(cr);
            Integer val=old+servPer;
            servPerQueue.put(cr,val);
        }
        else
        {
            servPerQueue.put(cr, servPer);
        }
    }

    /**
     * Media timpului in care cozile au fost goale.
     * @return int
     */
    public static int getEmptyQueueTime(){
        int sum=0;
        for(CashRegister cr:link.keySet()){
            sum+=emptyPerQueue.get(cr);
        }
        if(sum!=0){
            return sum/nrQueues;
        }
        return 0;
    }

    /**
     * Creste timpul in care coada e goala cu 1(o unitate de timp).
     * @param cr
     */
    public static void incEmptyTimePerQueue(CashRegister cr){
        if(emptyPerQueue.get(cr)!=null ) {
            int old = emptyPerQueue.get(cr);
            emptyPerQueue.put(cr,++old);
        }
        else
        {
            emptyPerQueue.put(cr, 1);
        }
    }
    public static int getCountEmpty(){
        return countEmpty;
    }
    /**
     * Calculeaza timpul de servire mediu pentru o coada.
     * @param cr
     * @return int
     */
    public static int calculateAvgServPerQueue(CashRegister cr){
        if(servPerQueue.get(cr)!=null && clientsInIntervalPerQueue.get(cr)!=null) {
            int total = servPerQueue.get(cr);
            return (total / clientsInIntervalPerQueue.get(cr));
        }
        return 0;
    }

    public static Map<CashRegister, ArrayList<Client>> getLink() {
        return link;
    }

    public static void setNrClientsGenerated(int nrClientsGenerated) {
        Statistics.nrClientsGenerated = nrClientsGenerated;
    }

    public static void setNrQueues(int nrQueues) {
        Statistics.nrQueues = nrQueues;
    }

    public static void setSimInterval(int simInterval) {
        Statistics.simInterval = simInterval;
    }

    /**
     * Calculeaza numarul de clienti care au sosit in intervalul [a,b]
     * @param a
     * @param b
     * @return int
     */
    public static int getNumberOfClientsInInterval(int a,int b){
        int nr=0;
        for(Map.Entry<CashRegister,ArrayList<Client>> l:link.entrySet()){
            for(Client c: l.getValue()){
                if(c.getArrivalTime()>=a && c.getArrivalTime()<=b){
                    nr++;
                }
            }
        }
        return nr;
    }

    /**
     * Cauta numarul de clienti intr-un interval de timp egal
     * ca durata cu parametrul timeSlice.
     * @param timeSlice
     * @return
     */
    public static String getGlobalPeakHour(int timeSlice) {
        int a=0;
        int b=timeSlice;
        int max=0;
        int c=a;
       while (b<=simInterval-timeSlice){//parcurge intervalul cu pasul timeSlice
           int value=getNumberOfClientsInInterval(a, b);
            if(value >= max){//preia maximul din fiecare interval
                max=value;
                c=a;//salveaza capatul inferior pentru peak
            }
           a=b;
           b+=timeSlice;

       }
       return ""+c+" to "+(c+timeSlice);
    }

}
