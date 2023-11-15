import React, { useState } from 'react'
import axios from 'axios';
import { confirmReset } from '../../utils/api/auth';
import { useNavigate } from 'react-router-dom';
import "../../css/Auth.css"
import HeaderUser from "../../components/Layout/HeaderUser";

const VerifyResetPasswordPage = () => {
    //Lấy dữ liệu
    const email = sessionStorage.getItem("email");
    const [password, setPassword] = useState("");
    const [vpassword, setVpassword] = useState("");
    const [verifycode, setVerifycode] = useState("");
    const navigate = useNavigate();
    const handleVerify = async() =>{
      if (password===vpassword){
        try{
          const reponse = await axios.post(confirmReset,
              {email, password, verifycode});
          if(reponse.status===200){
            alert("Lấy lại mật khẩu thành công");
            sessionStorage.removeItem("email");
            sessionStorage.removeItem("password");
            navigate("/")
          } else {
            alert("Lấy lại mật khẩu thất bại");
          }
        }catch(error){
          console.log(error);
          alert("Lỗi");
        }
      }
      else{
        alert("Mật khẩu không khớp")
      }
  }
  return (
    <div className='verifyreset'>
      <HeaderUser/>
      <div className="verifyreset-container">
        <div className="verifyreset-form">
          <h1> Nhập thông tin để lấy lại mật khẩu</h1>
          <input
            type="password"
            placeholder="Mật khẩu mới"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <input
            type="password"
            placeholder="Nhập lại mật khẩu mới"
            value={vpassword}
            onChange={(e) => setVpassword(e.target.value)}
          />
          <input
            type="text"
            placeholder="Code"
            value={verifycode}
            onChange={(e) => setVerifycode(e.target.value)}
          />
          <button onClick={handleVerify}>Xác nhận</button>
        </div>
      </div>
    </div>
  )
}

export default VerifyResetPasswordPage