import React, { useState } from 'react'
import axios from 'axios';
import { confirmRegister } from '../../api';
import { useNavigate } from 'react-router-dom';
import "../../css/Auth.css"
import HeaderUser from "../../components/Layout/HeaderUser";

const VerifyRegisterPage = () => {
    //Lấy dữ liệu
    const email = sessionStorage.getItem("email");
    const password = sessionStorage.getItem("password");
    const fullname = sessionStorage.getItem("fullname");

    const [verifycode, setVerifycode] = useState("");
    const navigate = useNavigate();
    const handleVerifyRegister = async() =>{
      try{
        const reponse = await axios.post(confirmRegister,
            {email, password, fullname, verifycode});
        if(reponse.status===201){
          alert("Đăng ký thành công");
          sessionStorage.removeItem("email");
          sessionStorage.removeItem("password");
          sessionStorage.removeItem("fullname");
          navigate("/")
        } else {
          alert("Đăng ký thất bại");
        }
      }catch(error){
        console.log(error);
        alert("Lỗi:");
      }
  }
  return (
    <div className="verify">
      <HeaderUser/>
      <div className="verification-container">
        <div className="verification-form">
          <h1> Hãy nhập mã xác thực</h1>
          <input
            type="text"
            placeholder="Code"
            value={verifycode}
            onChange={(e) => setVerifycode(e.target.value)}
          />
          <button onClick={handleVerifyRegister}>Xác nhận</button>
        </div>
      </div>
    </div>
  )
}

export default VerifyRegisterPage