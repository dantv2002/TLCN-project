import React, { useState, useEffect } from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'
import { useNavigate } from 'react-router-dom'
import {deletePatientRecordAdminApi,
        searchPatientRecordAdminApi } from '../../utils/api/admin'
import ReactPaginate from 'react-paginate'
import axios from 'axios'
import "../../css/Admin.css"
import moment from 'moment'

const PatientRecordAdmin = () => {
  const [records, setRecords] = useState([]);
  const [totalpage, setTotalpage] = useState(0);
  const [currentpage, setCurrentpage] = useState(0);
  const [eventClick, setEventClick] = useState();
  const [keyword, setKeyword] = useState("");
  const [searchClicked, setSearchClicked] = useState(false);
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  const handleSearch = () => {
    setSearchClicked(true);
    setCurrentpage(0);
  }

  useEffect(() => {
    if (!searchClicked) {
      const fetchPatientRecords = async () => {
        try {
          const response = await axios.get(searchPatientRecordAdminApi(keyword, currentpage), {
              headers: {
                  Authorization: `Bearer ${token}`,
              },
          });
          console.log(response.data.data.medicals)
          setRecords(response.data.data.medicals);
          setTotalpage(Math.ceil(response.data.data.total/5));
        } catch (error) {
            console.error('Lỗi khi lấy dữ liệu hồ sơ bệnh nhân', error);
        }
      };
      fetchPatientRecords();
    } else {
        const fetchPatientRecords = async () => {
          try {
            const response = await axios.get(searchPatientRecordAdminApi(keyword, currentpage), {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log(response.data.data.medicals)
            setRecords(response.data.data.medicals);
            setTotalpage(Math.ceil(response.data.data.total / 5));
          } catch (error) {
              console.error('Lỗi khi lấy dữ liệu hồ sơ bệnh nhân', error);
          }
        };
        setSearchClicked(false);
        fetchPatientRecords();
    }
  }, [token, currentpage, eventClick, searchClicked]);

  const handlePageClick = (data) => {
    const selectedPage = data.selected;
    setCurrentpage(selectedPage);
  }

  const handleCreatePatient = () => {
    navigate("/admin/dashboard/patientrecord/create");
  }

  const handleReadPatientRecord = (id) => {
    navigate(`/admin/dashboard/patientrecord/read/${id}`)
  }
  const handleUpdatePatientRecord = (id) => {
    navigate(`/admin/dashboard/patientrecord/update/${id}`)
  }
  const handleDeletePatientRecord = async(id) => {
    try {
      await axios.delete(deletePatientRecordAdminApi(id),{
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      if(eventClick){
        setEventClick(false);
      }else{
        setEventClick(true);
      }
      console.log("Đã xóa");
    }catch(error){
      console.log(error);
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
            <div className='record_container'>
              <h1>Danh sách hồ sơ bệnh nhân</h1>  
              <button className='create' onClick={handleCreatePatient}>Tạo hồ sơ bệnh án</button>
              <div className='search_patient'>
                    <form>
                        <input 
                            type="text" 
                            className='form-control'
                            placeholder="nhập CMND/CCCD hoặc tên bệnh nhân"
                            value={keyword}
                            onChange={(e) => setKeyword(e.target.value)}
                        />
                        <i className="ri-search-line" onClick={handleSearch}></i>
                    </form>
                </div>
                <table>
                  <thead>
                  <tr>
                      <th>Số thứ tự</th>
                      <th>Email</th>
                      <th>Họ tên</th>
                      <th>Ngày sinh</th>
                      <th>Số điện thoại</th>
                      <th>Tùy chọn</th>
                  </tr>
                  </thead>
                  <tbody>
                  {records.map((record, index) => (
                      <tr key={record.id}>
                      <td>{index+1 + currentpage*5}</td>
                      <td>{record.email}.</td>
                      <td>{record.fullName}</td>
                      <td>{moment(record.date).format("MM/DD/YYYY")}</td>
                      <td>{record.phonenumber}</td>
                      <td>
                        <button className='read' onClick={() => handleReadPatientRecord(record.id)}>Xem</button>
                        <button className='update' onClick={() => handleUpdatePatientRecord(record.id)}>Cập nhật</button>
                        <button className='delete' onClick={() => handleDeletePatientRecord(record.id)}>Xóa</button>
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

export default PatientRecordAdmin