import React, { useEffect, useState } from 'react'
import HeaderAdminDoctor from '../../Layout/HeaderAdminDoctor'
import AdminMenu from '../../Layout/AdminMenu'
import { createMedicalRecordAdminApi } from './../../../utils/api/admin';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import moment from 'moment';

const CreateMedicalRecordAdminForm = () => {
    const {id} = useParams();
    const [clinics, setClinics] = useState("");
    const [date, setDate] = useState("");
    const [clinicalDiagnosis, setClinicalDiagnosis] = useState("");
    const token = localStorage.getItem("token");

    console.log(id);

    const navigate = useNavigate();

    const handleCreateMedicalRecord = async(e) => {
        e.preventDefault();

        const dateFormatted = moment(date).format("MM/DD/YYYY");
        try {
            const reponse = await axios.post(createMedicalRecordAdminApi, 
            {
                clinics,
                date: dateFormatted,
                clinicalDiagnosis,
                patientId: id,
            },{
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (reponse.status === 201) {
                alert("Tạo hồ sơ bệnh án thành công");
                navigate("/admin/dashboard/medicalrecord");
            }
        } catch(error){
            console.log(error);
            alert("Tạo hồ sơ bệnh án không thành công");
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
                    <div className="create_patientrecord">
                            <form onSubmit={handleCreateMedicalRecord}>
                                <h1>Tạo hồ sơ bệnh án</h1>
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
                                <button type="submit" className="btn btn-primary">
                                    Tạo hồ sơ
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CreateMedicalRecordAdminForm