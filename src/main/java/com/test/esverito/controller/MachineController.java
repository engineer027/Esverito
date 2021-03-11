package com.test.esverito.controller;

import com.test.esverito.model.Machine;
import com.test.esverito.model.dto.MachineRequestDto;
import com.test.esverito.model.dto.MachineResponseDto;
import com.test.esverito.service.MachineMapper;
import com.test.esverito.service.MachineService;
import com.test.esverito.service.MyReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine")
public class MachineController {
    private final MachineService machineService;
    private final MyReader myReader;
    private final MachineMapper machineMapper;

    public MachineController(MachineService machineService,
                             MyReader myReader,
                             MachineMapper machineMapper) {
        this.machineService = machineService;
        this.myReader = myReader;
        this.machineMapper = machineMapper;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody MachineRequestDto machineRequestDto) {
        Machine machine = machineMapper.mapDto(machineRequestDto);
        machineService.save(machine);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{machineId}")
    public ResponseEntity<Void> update(@PathVariable Long machineId,
                          @RequestBody MachineRequestDto machineRequestDto) {
        Machine machine = machineMapper.mapDto(machineRequestDto);
        machine.setId(machineId);
        machineService.update(machine);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/by-id")
    public ResponseEntity<MachineResponseDto> getById(@RequestParam Long id) {
        Machine machine = machineService.get(id);
        MachineResponseDto dto = machineMapper.mapToDto(machine);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/owner")
    public ResponseEntity<List<MachineResponseDto>> findAllByOwner(
           @RequestParam String owner) {
        List<MachineResponseDto> dtos = machineService.getAllByOwner(owner)
                .stream()
                .map(machineMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/country")
    public ResponseEntity<List<MachineResponseDto>> findAllByCountry(
            @RequestParam String country) {
        List<MachineResponseDto> dtos = machineService.findAllByCountry(country)
                .stream()
                .map(machineMapper::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/db")
    public ResponseEntity<Void> update(@RequestParam String pathName) {
        File file = new File(pathName);
        myReader.readFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
