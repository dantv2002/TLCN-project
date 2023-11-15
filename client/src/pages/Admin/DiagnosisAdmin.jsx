import React from 'react'
import HeaderAdminDoctor from '../../components/Layout/HeaderAdminDoctor'
import AdminMenu from '../../components/Layout/AdminMenu'

const DiagnosisAdmin = () => {
  return (
    <div>
      <HeaderAdminDoctor/>
      <div className='container-fluid'>
        <div className='row'>
          <div className='col-md-3'>
            <AdminMenu/>
          </div>
          <div className='col-md-9'>Chẩn đoán hình ảnh</div>
        </div>
      </div>
    </div>
  )
}

export default DiagnosisAdmin
