package model;


import view.RegisterGrid;

import java.util.LinkedList;
import java.util.List;

public class Scheduler {
    private List<CashRegister> regs;
    private int maxNoRegs;
    private int maxClientsPerReg;
    private Strategy strategy;
    RegisterGrid panel;
    public Scheduler(int maxNoRegs, int maxClientsPerReg, RegisterGrid panel) {
        this.panel=panel;
        this.maxNoRegs = maxNoRegs;
        this.maxClientsPerReg = maxClientsPerReg;
        regs=new LinkedList<CashRegister>();
        for(int i=0;i<maxNoRegs;i++){//porneste fiecare casa sa lucreze individual
            CashRegister reg=new CashRegister(""+(i+1),maxClientsPerReg,panel);
            regs.add(reg);//salveaza lista de case de marcat
            Thread t=new Thread(reg);
            t.start();
        }

    }

    /**
     * Schimba strategia : numar minim de clienti
     * sau coada cu numar minim de asteptare.
     * @param policy
     */
    public void changeStrategy(SelectionPolicy policy){
        if(policy==SelectionPolicy.SHORTEST_QUEUE){
           // strategy=new ConcreteStrategyQueue();
        }
        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy=new ConcreteStrategyTime();
        }
    }

    /**
     * Adauga client prin strategia aleasa.
     * @param c
     */
    public void dispatchClient(Client c){

        strategy.addClient(regs,c);
    }
    public Strategy getStrategy(){
        return strategy;
    }
    public List<CashRegister> getRegs(){
        return regs;
    }
}
