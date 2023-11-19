import React, { useState, useEffect } from 'react'
import HeaderAdminDoctor from '../../Layout/HeaderAdminDoctor'
import AdminMenu from '../../Layout/AdminMenu'
import { useParams } from 'react-router-dom'
import { readMedicalRecordDetailAdminApi } from '../../../utils/api/admin'
import axios from 'axios'
import '../../../css/User.css'

const MedicalRecordDetailAdminForm = () => {  

  const { id } = useParams();
  const [medicalRecord, setMedicalRecord] = useState(null);
  const token = localStorage.getItem("token");

  useEffect(() => {
    const fetchMedicalRecordDetail = async () => {
      try {
        const response = await axios.get(readMedicalRecordDetailAdminApi(id), {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setMedicalRecord(response.data.data.medical);
        console.log(response.data.data.medical);
      } catch (error) {
        console.error('Lỗi khi lấy chi tiết hồ sơ bệnh án', error);
      }
    };

    fetchMedicalRecordDetail();
  }, [id]);


  return (
    <div>
      <HeaderAdminDoctor/>
      <div className='container-fluid'>
        <div className='row'>
          <div className='col-md-3'>
            <AdminMenu/>
          </div>
          <div className='col-md-9'>
            <h1>Chi tiết bệnh án</h1>
            {medicalRecord ? (
              <div className='medicalrecord_detail_container'>
                <p>Ngày khám: {medicalRecord.date}</p>
                <p>Tên bệnh nhân: {medicalRecord.namePatient}</p>
                <p>Tên bác sĩ: {medicalRecord.nameDoctor}</p>
                <p>Phòng khám: {medicalRecord.clinics}</p>
                <p>Chẩn đoán lâm sàng: {medicalRecord.clinicalDiagnosis}</p>
                <p>Chẩn đoán: {medicalRecord.diagnosis}</p>
                {medicalRecord.diagnosticImages ? (
                  <>
                    <p className='diagnosis-header'>Thông tin hình ảnh chẩn đoán:</p>
                    <p>Phương pháp: {medicalRecord.diagnosticImages.method}</p>
                    <p>Kết luận: {medicalRecord.diagnosticImages.conclude}</p>
                    <p>Nội dung: {medicalRecord.diagnosticImages.content}</p>
                    {medicalRecord.diagnosticImages.urlImage && (
                      <img src={medicalRecord.diagnosticImages.urlImage} alt="Diagnostic Image" />
                    )}
                  </>
                ) : (
                  <p>Không có thông tin hình ảnh chẩn đoán.</p>
                )}
              </div>
            ) : (
              <p>Loading...</p>
            )}
          </div> 
        </div>
      </div>
    </div>  
  )
}

export default MedicalRecordDetailAdminForm