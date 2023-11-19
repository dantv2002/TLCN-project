import React from 'react'
import { NavLink } from 'react-router-dom'
import "../../css/Admin.css"

const AdminMenu = () => {
  return (
    <>
    <div className='text-center'>
        <div class="list-group">
            <NavLink to="/admin/dashboard/account" className="list-group-item list-group-item-action">Quản lý tài khoản</NavLink>
            <NavLink to="/admin/dashboard/patientrecord" className="list-group-item list-group-item-action">Quản lý hồ sơ bệnh nhân</NavLink>
            <NavLink to="/admin/dashboard/medicalrecord" className="list-group-item list-group-item-action">Quản lý hồ sơ bệnh án</NavLink>
            <NavLink to="/admin/dashboard/diagnosis" className="list-group-item list-group-item-action">Chẩn đoán hình ảnh</NavLink>
        </div>
    </div>
    </>
  )
}

export default AdminMenu