package edu.iu.dcrispin.GumballMachine.service;

import edu.iu.dcrispin.GumballMachine.model.*;
import edu.iu.dcrispin.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public enum Transition {
        INSERT_QUARTER, EJECT_QUARTER, TURN_CRANK, REFILL
    }

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    private TransitionResult transit(String id, Transition action, int count) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        IGumballMachine machine = new GumballMachine2(record.getId(), record.getState(), record.getCount());
        TransitionResult result = runTheMachine(machine, action, count);
        if(result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    private TransitionResult runTheMachine(IGumballMachine machine, Transition action, int count) {
        switch (action) {
            case INSERT_QUARTER -> {
                return machine.insertQuarter();
            }
            case EJECT_QUARTER -> {
                return machine.ejectQuarter();
            }
            case TURN_CRANK -> {
                return machine.turnCrank();
            }
            case REFILL -> {
                return machine.refillMachine(count);
            }
        }
        return null;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return transit(id, Transition.INSERT_QUARTER, 0);
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return transit(id, Transition.EJECT_QUARTER, 0);
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return transit(id, Transition.TURN_CRANK, 0);
    }

    public TransitionResult refill(String id, int count) throws IOException {
        return transit(id, Transition.REFILL, count);
    }

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}