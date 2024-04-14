package edu.iu.dcrispin.GumballMachine.repository;

import edu.iu.dcrispin.GumballMachine.model.GumballMachineRecord;

import java.io.IOException;
import java.util.List;

public interface IGumballRepository {
    List<GumballMachineRecord> findAll() throws IOException;

    GumballMachineRecord findById(String id) throws IOException;

    String save(GumballMachineRecord gumballMachineRecord) throws IOException;
}
