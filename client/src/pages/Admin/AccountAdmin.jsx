import React, { useState, useEffect } from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'
import { readAccoutApi, 
        lockAccountApi, 
        unlockAccountApi,
        deleteAccountApi } from '../../utils/api/admin'
import axios from 'axios'
import ReactPaginate from 'react-paginate'
import { useNavigate } from 'react-router-dom'

const AccountAdmin = () => {
  const [account, setAccount] = useState([]);
  const [totalpage, setTotalpage] = useState(0);
  const [currentpage, setCurrentpage] = useState(0);
  const [eventClick, setEventClick] = useState();
  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  useEffect(() => {
      const fetchMedicalRecords = async () => {
        try {
          const response = await axios.get(readAccoutApi(currentpage), {
              headers: {
                  Authorization: `Bearer ${token}`,
              },
          });
          console.log(response.data.data.medicals)
          setAccount(response.data.data.medicals);
          setTotalpage(Math.ceil(response.data.data.total / 5));
        } catch (error) {
            console.error('Lỗi khi lấy dữ liệu tài khoản', error);
        }
      };
      fetchMedicalRecords();
  }, [token, currentpage, eventClick]);

  const handlePageClick = (data) => {
      const selectedPage = data.selected;
      setCurrentpage(selectedPage);
  }

  const handleLock = async(id) => {
    try {
      await axios.get(lockAccountApi(id),{
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      console.log("Đã khóa");
      setEventClick(false);
    }catch(error){
      console.log(error);
    }
  }

  const handleUnlock = async(id) => {
    try {
      await axios.get(unlockAccountApi(id),{
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      console.log("Đã mở khóa");
      setEventClick(true);
    }catch(error){
      console.log(error);
    }
  }

  const handleRePassword = (id) =>{
    navigate(`/admin/dashboard/account/repass/${id}`);
  }

  const handleCreateAccoutDoctor = () =>{
    navigate("/admin/dashboard/account/createdoctor");
    setEventClick(true);
  }

  const handleDeleteAccount = async(id) =>{
    try {
      await axios.delete(deleteAccountApi(id),{
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
  function convertRoleToVietnamese(role) {
    switch (role) {
      case 'PATIENT':
        return 'Bệnh nhân';
      case 'DOCTOR':
        return 'Bác sĩ';
      default:
        return role; 
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
            <div className='account_container'>
              <h1>Danh sách tài khoản</h1>
              <button className='unlock' onClick={handleCreateAccoutDoctor}>Tạo tài khoản cho bác sĩ</button>
                <table>
                    <thead>
                    <tr>
                        <th>Số thứ tự</th>
                        <th>Email</th>
                        <th>Tình trạng</th>
                        <th>Vai trò</th>
                        <th>Tùy chọn</th>
                    </tr>
                    </thead>
                    <tbody>
                    {account.map((tk, index) => (
                        <tr key={tk.id}>
                        <td>{index+1 + currentpage*5}</td>
                        <td>{tk.email}.</td>
                        <td>{tk.status ? "Đang sử dụng" : "Đã khóa"}</td>
                        <td>{convertRoleToVietnamese(tk.role)}</td>
                        <td>
                          {tk.status ? (
                            <button className="lock" onClick={() => handleLock(tk.id)}>Khóa</button>
                          ) : (
                            <button className="unlock" onClick={() => handleUnlock(tk.id)}>Mở</button>
                          )}
                          <button className='repass' onClick={() => handleRePassword(tk.id)}>Cấp lại MK</button>
                          <button className='delete' onClick={() => handleDeleteAccount(tk.id)}>Xóa</button>
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

export default AccountAdmin