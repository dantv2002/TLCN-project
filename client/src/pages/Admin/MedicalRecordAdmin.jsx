import React, {useState, useEffect} from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'
import { useNavigate } from 'react-router-dom'
import ReactPaginate from 'react-paginate'
import axios from 'axios'
import moment from 'moment'
import { searchMedicalRecordAdminApi,
         deleteMedicalRecordAdminApi, 
         searchAllPatientRecordAdminApi} from '../../utils/api/admin'


const MedicalRecordAdmin = () => {
  const [medicalRecords, setMedicalRecords] = useState([]);
  const [patientRecords, setPatientRecords] = useState([]);
  const [patient, setPatient] = useState("");
  const [totalpage, setTotalpage] = useState(0);
  const [currentpage, setCurrentpage] = useState(0);
  const [keywordPatient, setKeywordPatient] = useState("");
  const [keywordMedical, setKeywordMedical] = useState("");
  const [searchClicked, setSearchClicked] = useState(false);
  const [deleteClicked, setDeleteClicked] = useState();
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  const handleSearchMedical = () => {
      setSearchClicked(true);
      setCurrentpage(0);
  };

  const handleCreateMedicalRecord = (id) => {
    navigate(`/admin/dashboard/medicalrecord/create/${id}`)
  }
  const selectPatient = (id) =>{
    setPatient(id.target.value)
  }
  useEffect(() => {
      if (keywordMedical === "") {
          const fetchMedicalRecords = async () => {
              try {
                  const response = await axios.get(searchMedicalRecordAdminApi(keywordMedical, currentpage), {
                      headers: {
                          Authorization: `Bearer ${token}`,
                      },
                  });
                  console.log(response.data.data.medicals)
                  console.log(currentpage);
                  setMedicalRecords(response.data.data.medicals);
                  setTotalpage(Math.ceil(response.data.data.total / 5));
              } catch (error) {
                  console.error('Lỗi khi lấy dữ liệu bệnh án', error);
              }
          };
          console.log(searchClicked);
          fetchMedicalRecords();
      } else if (searchClicked && keywordMedical !== "") {
          const fetchMedicalRecords = async () => {
              try {
                  const response = await axios.get(searchMedicalRecordAdminApi(keywordMedical, currentpage), {
                      headers: {
                          Authorization: `Bearer ${token}`,
                      },
                  });
                  console.log(response.data.data.medicals)
                  setMedicalRecords(response.data.data.medicals);
                  setTotalpage(Math.ceil(response.data.data.total / 5));
              } catch (error) {
                  console.error('Lỗi khi lấy dữ liệu bệnh án', error);
              }
          };
          setSearchClicked(false);
          console.log(searchClicked);
          fetchMedicalRecords();
      }
  }, [token, currentpage, searchClicked, deleteClicked]);

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
  const handleDeleteMedicalRecord = async(id) => {
    try {
      await axios.delete(deleteMedicalRecordAdminApi(id),{
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      if(deleteClicked){
        setDeleteClicked(false);
      }else{
        setDeleteClicked(true);
      }
      console.log("Đã xóa");
    }catch(error){
      console.log(error);
    }
  }

  const handleReadMedicalRecordDetail = (id) => {
    navigate(`/admin/dashboard/medicalrecord/detail/${id}`)
  }
  
  const handleUpdateMedicalRecord = (id) => {
    navigate(`/admin/dashboard/medicalrecord/update/${id}`);
  }

  const handlePageClick = (data) => {
      const selectedPage = data.selected;
      setCurrentpage(selectedPage);
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
            <div className='medicalrecord_container'>
                <h5> Tạo hồ sơ bệnh án</h5>
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
                    <button className='create_medical' onClick={() => handleCreateMedicalRecord(patient)}>Tạo bệnh án</button>
                </div>
                <h1>Danh sách hồ sơ bệnh án</h1>
                <div className='search_medical'>
                    <form>
                        <input 
                            type="text" 
                            className='form-control'
                            placeholder="nhập bệnh án"
                            value={keywordMedical}
                            onChange={(e) => setKeywordMedical(e.target.value)}
                        />
                        <i className="ri-search-line" onClick={handleSearchMedical}></i>
                    </form>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>Số thứ tự</th>
                        <th>Ngày khám</th>
                        <th>Tên bệnh nhân</th>
                        <th>Tên bác sĩ</th>
                        <th>Tùy chọn</th>
                    </tr>
                    </thead>
                    <tbody>
                    {medicalRecords.map((record, index) => (
                        <tr key={record.id}>
                        <td>{index+1 + currentpage*5}</td>
                        <td>{moment(record.date).format("DD/MM/YYYY")}.</td>
                        <td>{record.namePatient}</td>
                        <td>{record.nameDoctor}</td>
                        <td>
                            <button className='read' onClick={() => handleReadMedicalRecordDetail(record.id)}>Xem</button>
                            <button className='update' onClick={() => handleUpdateMedicalRecord(record.id)}>Cập nhật</button>
                            <button className='delete' onClick={() => handleDeleteMedicalRecord(record.id)}>Xóa</button>
                        </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <ReactPaginate
                    nextLabel="Sau >"
                    onPageChange={handlePageClick}
                    pageRangeDisplayed={3}
                    marginPagesDisplayed={2}
                    pageCount={totalpage}
                    previousLabel="< Trước"
                    pageClassName="page-item"
                    pageLinkClassName="page-link"
                    previousClassName="page-item"
                    previousLinkClassName="page-link"
                    nextClassName="page-item"
                    nextLinkClassName="page-link"
                    breakLabel="..."
                    breakClassName="page-item"
                    breakLinkClassName="page-link"
                    containerClassName="pagination"
                    activeClassName="active"
                    renderOnZeroPageCount={null}
                />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default MedicalRecordAdmin