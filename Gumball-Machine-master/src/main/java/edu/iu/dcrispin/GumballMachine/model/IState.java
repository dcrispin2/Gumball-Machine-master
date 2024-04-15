package edu.iu.dcrispin.GumballMachine.model;

public interface IState {
    TransitionResult insertQuarter();
    TransitionResult ejectQuarter();
    TransitionResult turnCrank();
    TransitionResult dispense();

    TransitionResult refill(int num);
    String getTheName();


}

