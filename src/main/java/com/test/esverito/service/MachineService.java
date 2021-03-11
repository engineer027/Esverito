package com.test.esverito.service;

import com.test.esverito.model.Machine;

import java.util.List;

public interface MachineService {
    Machine get (Long id);

    Machine save (Machine machine);

    Machine update (Machine machine);

    List<Machine> getAllByOwner(String owner);

    List<Machine> findAllByCountry(String country);







}
