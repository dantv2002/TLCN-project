package com.medical.springboot.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.medical.springboot.models.entity.MedicalEntity;

public interface MedicalRepository extends MongoRepository<MedicalEntity, String> {

    @Query(value = "{ 'patientId' : ?0 }", fields = "{'patientId' : 1, 'date' : 1,'doctorId' : 1  }")
    public Page<MedicalEntity> findByPatientId(String patientId, Pageable pageable);

    @Query(value = "{ $and: [ { 'diagnosis' : { $regex: ?0 , $options: 'i' } }, { 'patientId': ?1 } ] }", fields = "{'patientId' : 1, 'date' : 1,'doctorId' : 1  }")
    public Page<MedicalEntity> find(String keyword, String patientId, Pageable pageable);

    @Query(value = "{ 'diagnosis' : { $regex: ?0 , $options: 'i' } }", fields = "{'patientId' : 1, 'date' : 1,'doctorId' : 1  }")
    public Page<MedicalEntity> findByKeyword(String keyword, Pageable pageable);

    // statistical
    @Query(value = "{$and: [ { 'doctorId' : { $regex: ?0 } }, { 'createdDate' : { $gte: ?1 } }, { 'createdDate' : { $lte: ?2 } } ] }")
    public List<MedicalEntity> findByDateBetween(String doctor, Date startDate, Date endDate);
    // get all medicals of doctor
    @Query(value = "{ 'doctorId' : ?0 }")
    public List<MedicalEntity> findAllPatientIdByDoctorId(String doctorId);
}
