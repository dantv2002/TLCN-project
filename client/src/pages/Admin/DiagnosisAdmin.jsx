import React, { useState, useEffect } from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'
import UploadImage from '../../data/UploadImage'
import axios from 'axios'
import { diagnosisImageApi, 
        saveDiagnosisImageApi, 
        searchAllMedicalRecordAdminApi, 
        searchAllPatientRecordAdminApi } from '../../utils/api/admin'
import moment from 'moment';
import "../../css/Admin.css";

const DiagnosisAdmin = () => {

  const token = localStorage.getItem("token");
  const [image, setImage] = useState("");
  const [result, setResult] = useState("Bình thường");
  const [keywordPatient, setKeywordPatient] = useState("");
  const [patientRecords, setPatientRecords] = useState([]);
  const [patient, setPatient] = useState("");
  const [keywordMedical, setKeywordMedical] = useState("");
  const [medicalRecords, setMedicalRecords] = useState([]);
  const [medical, setMedical] = useState("");
  const [method, setMethod] = useState("");
  const [content, setContent] = useState("");

  const handleImageUpload = (url) => {
    setImage(url);
  };

  const handleDiagnosisImage = async() => {
    try {
      const response = await axios.post(diagnosisImageApi, 
      {
        image,
      },{
        headers: {
            Authorization: `Bearer ${token}`,
        },
      });
      if (response.status === 200){
        setResult(response.data.data.result);
        console.log(response);
      }
    }catch(error){
      console.log(error);
      alert("Lỗi không thấy kết quả");
    }
  }

  const selectPatient = (id) =>{
    setPatient(id.target.value)
  }

  useEffect(() => {
    const fetchPatientRecords = async () => {
      try {
        const res = await axios.get(searchAllPatientRecordAdminApi(keywordPatient), {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setPatientRecords(res.data.data.medicals);
      }catch(error){
      console.log(error);
      }
    }
    fetchPatientRecords();
  }, [token, keywordPatient]);
  useEffect(() => {
    if (patientRecords.length > 0) {
      setPatient(patientRecords[0].id);
    }
  },[patientRecords])

  const selectMedical = (id) =>{
    setMedical(id.target.value)
  }

  useEffect(() => {
    const fetchMedicalRecords = async () => {
      try {
        const res = await axios.get(searchAllMedicalRecordAdminApi(keywordMedical), {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setMedicalRecords(res.data.data.medicals);
      }catch(error){
      console.log(error);
      }
    }
    fetchMedicalRecords();
  }, [token, keywordMedical]);

  useEffect(() => {
    if (medicalRecords.length > 0) {
      setMedical(medicalRecords[0].id);
    }
  },[medicalRecords])

  const handleSaveResult = async(id) => {
    try {
      const response = await axios.post(saveDiagnosisImageApi(id), 
      {
        method,
        content,
        urlImage: image,
        conclude: result,
      },{
        headers: {
            Authorization: `Bearer ${token}`,
        },
      });
      if (response.status === 200){
        console(response);
        alert("Lưu thành công");
      }
    }catch(error){
      console.log(error);
      alert("Lưu không thành công")
    }
  }
  return (
    <div>
      <HeaderAdminDoctor/>
      <div className='container-fluid'>
        <div className='row'>
          <div className='col-md-3'>
            <AdminMenu/>
          </div>
          <div className='col-md-9'>
            <div className='row'>
              <div className='col-md-6'>
                <div className='readimage'>
                  <UploadImage onImageUpload={handleImageUpload}/>
                  <button className='redict' onClick={handleDiagnosisImage}>Chuẩn đoán</button>
                  <br/>
                  <span>Kết quả: {result}</span>
                </div>
              </div>
              <div className='col-md-6'>
                {image ? (
                <div className='saveimage'>
                  <div className='search_patient'>
                    <form>
                        <input 
                            type="text" 
                            className='form-control'
                            placeholder="nhập CMND/CCCD hoặc tên bệnh nhân"
                            value={keywordPatient}
                            onChange={(e) => setKeywordPatient(e.target.value)}
                        />
                    </form>
                    <select className='form-select' onChange={selectPatient}>
                      {patientRecords.map(patient => (
                        <option value={patient.id}>{patient.email} - {patient.fullName}</option>
                      ))}
                    </select>
                  </div>
                  <div className='search_medical'>
                    <form>
                        <input 
                            type="text" 
                            className='form-control'
                            placeholder="nhập kết quả bệnh án"
                            value={keywordMedical}
                            onChange={(e) => setKeywordMedical(e.target.value)}
                        />
                    </form>
                    <select className='form-select' onChange={selectMedical}>
                      {medicalRecords.map(medical => (
                        <option value={medical.id}>ID: {medical.id} - Ngày khám: {moment(medical.date).format("MM/DD/YYYY")} - Bệnh nhân: {medical.namePatient} - Bác sĩ: {medical.nameDoctor}</option>
                      ))}
                    </select>
                  </div>
                  <form onSubmit={() =>handleSaveResult(medical)}>
                      <h1>Lưu kết quả</h1>
                      <div className="mb-3">
                          <input
                              type="text"
                              className="form-control"
                              placeholder="Phương pháp"
                              value={method}
                              onChange={(e) => setMethod(e.target.value)}
                          />
                      </div>
                      <div className="mb-3">
                          <input
                              type="text"
                              className="form-control"
                              placeholder="Nội dung"
                              value={content}
                              onChange={(e) => setContent(e.target.value)}
                          />
                      </div>
                      <button type="submit" className="btn btn-primary">
                          Lưu
                      </button>
                  </form>
                </div>
                ) : (
                  <h2>Hãy load tải ảnh lên</h2>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DiagnosisAdmin
