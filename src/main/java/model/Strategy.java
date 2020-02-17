package model;

import java.util.List;

public interface Strategy {
    public void addClient(List<CashRegister> regs,Client c);

}
