package model;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    private static int minIndex=0;
    private int minWaitTime;

    /**
     * Aduaga client la coada cu numarul minim de clienti.
     * @param regs - casele de marcat in functiune
     * @param c - clientul de adaugat
     * return - void
     */
    public void addClient(List<CashRegister> regs, Client c) {
        minWaitTime=regs.get(minIndex).getWaitingPeriod().get();//minimul curent
        for(int i=0;i<regs.size();i++){//cauta alta coada cu numar minim de clienti
            int val=regs.get(i).getWaitingPeriod().get();
            if(val < minWaitTime){
                minWaitTime= val ;
                minIndex=i;
            }
        }
        regs.get(minIndex).addClient(c);//pune clientul la coada cu numarul minim
    }

}
