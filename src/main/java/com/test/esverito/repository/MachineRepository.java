package com.test.esverito.repository;

import com.test.esverito.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> getAllByOwner(String owner);

    List<Machine> getAllByCountry(String country);
}
