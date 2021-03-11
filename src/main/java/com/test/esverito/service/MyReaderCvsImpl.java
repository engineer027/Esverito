package com.test.esverito.service;

import com.test.esverito.model.Machine;
import com.test.esverito.repository.MachineRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class MyReaderCvsImpl implements MyReader {
    private final MachineMapper machineMapper;
    private final MachineRepository machineRepository;

    public MyReaderCvsImpl(MachineMapper machineMapper,
                           MachineRepository machineRepository) {
        this.machineMapper = machineMapper;
        this.machineRepository = machineRepository;
    }

    @Override
    public void readFile(File file) {
        LineIterator it = null;
        Boolean skipFirstLine = false;
        try {
            it = FileUtils.lineIterator(file, "UTF-8");
            String line = it.nextLine();
            while (it.hasNext() && !(line.length() == 0)) {
                if (!skipFirstLine) {
                    skipFirstLine = true;
                    line = it.nextLine();
                    continue;
                }
                Machine machine = machineMapper.mapperToMachine(line);
                machineRepository.save(machine);
                line = it.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
