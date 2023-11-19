import React from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'
import bg_admin from '../../assets/admin/bg_admin.jpg'

const DashboardAdmin = () => {
  return (
    <div>
      <HeaderAdminDoctor/>
      <div className='container-fluid'>
        <div className='row'>
          <div className='col-md-3'>
            <AdminMenu/>
          </div>
          <div className='col-md-9'>
            <h1> Xin chào Admin</h1>
            <img src={bg_admin} alt='background admin'></img>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DashboardAdmin