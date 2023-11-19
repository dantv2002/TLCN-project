import React, { useState, useEffect} from 'react'
import { readPatientRecordAdminApi } from '../../../utils/api/admin';
import axios from 'axios';
import moment from 'moment';
import HeaderAdminDoctor from '../../Layout/HeaderAdminDoctor';
import AdminMenu from '../../Layout/AdminMenu';
import { useParams } from 'react-router-dom';



const PatientRecordDetailAdminForm = () => {

  const { id } = useParams();
  const [record, setRecord] = useState(null);
  const token = localStorage.getItem("token");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchPatientRecordDetail = async () => {
      try {
        const response = await axios.get(readPatientRecordAdminApi(id), {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setRecord(response.data.data.patient);
        console.log(response.data.data.patient);
      } catch (error) {
        setError(true);
        console.error('Lỗi khi lấy chi tiết hồ sơ bệnh án', error);
      } finally {
        setLoading(false);
      }
    };
    
    const timeoutId = setTimeout(() => {
      if (loading) {
          setLoading(false);
          setError(true);
      }
    }, 3000);

    fetchPatientRecordDetail();

    return () => clearTimeout(timeoutId);

  }, [id, loading]);

  const getGenderString = (isMale) => {
    return isMale ? 'Nam' : 'Nữ';
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
            <div className='record_detail'>
              <h1>Chi tiết hồ sơ</h1>
              {loading ? (
                <div className='loading-message'>Loading...</div>
              ) : error ? (
                <div className='error-message'>Chưa tạo hồ sơ</div>
              ) : record ? (
                  <div className='record_detail_container'>
                    <p>ID: {record.id}</p>
                    <p>Họ tên: {record.fullName}</p>
                    <p>Ngày sinh: {moment(record.birthday).format("MM/DD/YYYY")}</p>
                    <p>Giới tính: {getGenderString(record.gender)}</p>
                    <p>Địa chỉ: {record.address}</p>
                    <p>Số điện thoại: {record.phonenumber}</p>
                    <p>Email: {record.email}</p>
                    <p>CMND/CCCD: {record.identificationCard}</p>
                    <p>Bệnh nền: {record.allergy}</p>
                    <p>Mã BHYT: {record.healthInsurance}</p>
                  </div>
                ) : (
                  <div className='error-message'>Chưa tạo hồ sơ</div>
              )}
            </div>   
          </div>
        </div>
      </div>
    </div>
  )
}

export default PatientRecordDetailAdminForm