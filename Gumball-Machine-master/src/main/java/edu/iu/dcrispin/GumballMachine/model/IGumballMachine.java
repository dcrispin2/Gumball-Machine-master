package edu.iu.dcrispin.GumballMachine.model;

public interface IGumballMachine {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();

    TransitionResult dispense();

    TransitionResult refillMachine(int num);

    void refill(int num);

    void changeTheStateTo(GumballMachineState name);
    Integer getCount();
    String getTheStateName();

    void releaseBall();
}
