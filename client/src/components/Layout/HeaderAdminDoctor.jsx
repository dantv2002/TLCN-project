import React from "react";
import logo from "../../assets/logo.png"
import axios from "axios";
import { logoutApi } from "../../api";
import "../../css/Home/Navbar.css";
import "../../css/Header.css"
import { useNavigate } from "react-router-dom";

function HeaderAdminDoctor() {

  const fullname = localStorage.getItem("fullname");
  const navigate = useNavigate();

  const handleLogout = async () => {

    const token = localStorage.getItem("token");  
    try{
      const response = await axios.get(logoutApi, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }); 
      if (response.status === 200) {
        localStorage.removeItem("email");
        localStorage.removeItem("fullname");
        localStorage.removeItem("token");
        localStorage.removeItem("role");
        alert("Đăng xuất thành công")
        navigate("/")
      } else {
        alert("Đăng xuất thất bại")
      }
    } catch(error) {
      console.log(error);
      alert("Lỗi")
    }
  }

  return (
    <div className="navbar-section">
      <h1 className="navbar-title">
        <span className="navbar-sign">
            <img className="image_logo" src={logo} alt="logo"/>
        </span>
      </h1>
      {!fullname ? (
        <ul className="navbar_LoRe">
          <li>
            <ion-icon name="person-outline"></ion-icon>
          </li>
          <li>
            <a href="/login" className="navbar-link">
              Đăng nhập
            </a>
          </li>
          <li>
            <a href="/register" className="navbar-link">
              Đăng ký
            </a>
          </li>
        </ul>
      ) : (
        <div className="options">
          <span>{fullname}</span>
          <i class="ri-user-line"></i>
          <div className="dropdown">
            <i class="ri-arrow-down-s-line dropdown__arrow"></i>
            <div className="dropdown_menu">
              <a href="/admin/changepassword">Đổi mật khẩu</a>
              <a onClick={handleLogout}>Đăng xuất</a>
            </div>
          </div>
        </div>
      )}
      
    </div>
  );
}

export default HeaderAdminDoctor;
