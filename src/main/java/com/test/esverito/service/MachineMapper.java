package com.test.esverito.service;

import com.test.esverito.model.Machine;
import com.test.esverito.model.dto.MachineRequestDto;
import com.test.esverito.model.dto.MachineResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MachineMapper {
    private static final String SYMBOL = ",";

    public Machine mapperToMachine(String text) {
        Machine machine = new Machine();
        String[] textString = text.split(SYMBOL);
        machine.setOwner(textString[0]);
        machine.setAvailable(Boolean.parseBoolean(textString[1]));
        machine.setCountry(textString[2]);
        machine.setCurrency(textString[3]);
        machine.setUrl(textString[textString.length-1]);
        machine.setSource(textString[textString.length-2]);
        machine.setPrice(Double.parseDouble(textString[textString.length-3]));
        machine.setId(Long.parseLong(textString[textString.length-4]));
        machine.setPhotos(textString[textString.length-5]);
        machine.setMachineType(textString[textString.length-6]);
        StringBuilder MachineInfoBuilder = new StringBuilder();
        for(int i = 4; i<textString.length-6; i++) {
            MachineInfoBuilder.append(textString[i]);
        }
        machine.setMachineInfo(MachineInfoBuilder.toString());
        return machine;
    }

    public MachineResponseDto mapToDto (Machine machine) {
        MachineResponseDto machineResponseDto = new MachineResponseDto();
        machineResponseDto.setOwner(machine.getOwner());
        machineResponseDto.setCountry(machine.getCountry());
        machineResponseDto.setCurrency(machine.getCurrency());
        machineResponseDto.setUrl(machine.getUrl());
        machineResponseDto.setSource(machine.getSource());
        machineResponseDto.setPrice(machine.getPrice());
        machineResponseDto.setId(machine.getId());
        machineResponseDto.setPhotos(machine.getPhotos());
        machineResponseDto.setMachineType(machine.getMachineType());
        machineResponseDto.setMachineInfo(machine.getMachineInfo());
        return machineResponseDto;
    }

    public Machine mapDto (MachineRequestDto machineRequestDto) {
        Machine machine = new Machine();
        machine.setOwner(machineRequestDto.getOwner());
        machine.setCountry(machineRequestDto.getCountry());
        machine.setCurrency(machineRequestDto.getCurrency());
        machine.setUrl(machineRequestDto.getUrl());
        machine.setSource(machineRequestDto.getSource());
        machine.setPrice(machineRequestDto.getPrice());
        machine.setPhotos(machineRequestDto.getPhotos());
        machine.setMachineInfo(machineRequestDto.getMachineInfo());
        machine.setMachineType(machineRequestDto.getMachineType());
        machine.setId(machineRequestDto.getId());
        return machine;
    }
}
