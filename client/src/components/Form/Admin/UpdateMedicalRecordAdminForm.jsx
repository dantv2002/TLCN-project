import React, {useEffect, useState} from 'react'
import HeaderAdminDoctor from '../../Layout/HeaderAdminDoctor'
import AdminMenu from '../../Layout/AdminMenu'
import axios from 'axios'
import moment from 'moment'
import { useNavigate, useParams } from 'react-router-dom'
import { readMedicalRecordDetailAdminApi, 
        updateMedicalRecordAdminApi } from '../../../utils/api/admin'


const UpdateMedicalRecordAdminForm = () => {

    const [medicalData, setMedicalData] = useState(null);
    const token = localStorage.getItem("token");
    const {id} = useParams();
    const [clinics, setClinics] = useState("");
    const [date, setDate] = useState("");
    const [clinicalDiagnosis, setClinicalDiagnosis] = useState("");
    const [diagnosis, setDiagnosis] = useState("");
    const navigate = useNavigate();
  
    useEffect(() => {
      const fetchData = async () => {
        try {
          const response = await axios.get(readMedicalRecordDetailAdminApi(id), {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          console.log(response.data.data.medical.date);
          setMedicalData(response.data.data.medical);
        } catch (error) {
          console.log(error);
          alert('Lỗi');
        }
      };
      fetchData();
    }, [token]);
  
    useEffect(() => {
      if (medicalData) {
        setClinics(medicalData.clinics)
        setDate(moment(medicalData.date).format("YYYY-MM-DD"));
        setClinicalDiagnosis(medicalData.clinicalDiagnosis);
        setDiagnosis(medicalData.diagnosis);
      }
    }, [medicalData]);
  
    const handleUpdateMedicalRecord = async (e) => {
      e.preventDefault();
      const dateFormatted = moment(date).format("MM/DD/YYYY");
      try {
        const res = await axios.put(updateMedicalRecordAdminApi(id),
          {
            clinics,
            date: dateFormatted,
            clinicalDiagnosis,
            diagnosis,
          },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        if (res.status === 200) {
          alert("Chỉnh sửa bệnh án thành công");
          navigate("/admin/dashboard/medicalrecord");
        }
      } catch (error) {
        console.log(error);
        alert("Chỉnh sửa bệnh án không thành công");
      }
    };
    return (
        <div>
            <HeaderAdminDoctor/>
            <div className='container-fluid'>
            <div className='row'>
                <div className='col-md-3'>
                <AdminMenu/>
                </div>
                <div className='col-md-9'>
                <div className="update_medicalrecord">
                        <form onSubmit={handleUpdateMedicalRecord}>
                            <h1>Chỉnh sửa bệnh án</h1>
                            <div className="mb-3">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Phòng khám"
                                value={clinics}
                                onChange={(e) => setClinics(e.target.value)}
                            />
                            </div>
                            <div className="mb-3">
                                <input
                                    type="date"
                                    className="form-control"
                                    placeholder="Ngày khám"
                                    value={date}
                                    onChange={(e) => setDate(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Chuẩn đoán lâm sàng"
                                    value={clinicalDiagnosis}
                                    onChange={(e) => setClinicalDiagnosis(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Chuẩn đoán"
                                    value={clinics}
                                    onChange={(e) => setClinics(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Chỉnh sửa
                            </button>
                        </form>
                    </div>
                </div>  
            </div>
        </div>
    </div>
  )
}

export default UpdateMedicalRecordAdminForm