package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    public PatientRepository patientRepository;
    public PatientMapper patientMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository ,PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
//
//        List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(patient -> PatientMapper.toPatientResponseDTO(patient)).toList();
//
//        List<PatientResponseDTO> patientResponseDTOS = new ArrayList<>();
//        for (Patient patient : patients) {
//            patientResponseDTOS.add(PatientMapper.toPatientResponseDTO(patient));
//        }
//        return patientResponseDTOS;
        List<PatientResponseDTO> patientResponseDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            PatientResponseDTO dto = patientMapper.toPatientResponseDTO(patient);
            patientResponseDTOS.add(dto);
        }
        return patientResponseDTOS;
    }
}
