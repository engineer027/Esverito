package com.test.esverito.service;

import com.test.esverito.model.Machine;
import com.test.esverito.repository.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;

    public MachineServiceImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Machine get(Long id) {
        return machineRepository.getOne(id);
    }

    @Override
    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public Machine update(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public List<Machine> getAllByOwner(String owner) {
        return machineRepository.getAllByOwner(owner);
    }

    @Override
    public List<Machine> findAllByCountry(String country) {
        return machineRepository.getAllByCountry(country);
    }
}
