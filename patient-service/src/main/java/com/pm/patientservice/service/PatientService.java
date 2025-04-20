package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExixstException;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
       if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
           throw new EmailAlreadyExixstException("Patient Email already exists" + patientRequestDTO.getEmail());
       }

        Patient newPatient = patientRepository.save(patientMapper.toPatientRequestDTO(patientRequestDTO));
        return patientMapper.toPatientResponseDTO(newPatient);
    }
}
