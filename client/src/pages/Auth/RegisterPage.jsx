import React, { useState } from "react";
import axios from "axios";
import { registerApi } from "../../utils/api/auth";
import { useNavigate } from "react-router-dom";
import "../../css/Auth.css"
import HeaderUser from "../../components/Layout/HeaderUser";

const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [vpassword, setVpassword] = useState("");
  const [fullname, setFullname] = useState("");

  const navigate = useNavigate()
  const handleRegister = async () => {
    if (password===vpassword){
      try {

        const response = await axios.post(registerApi, {
              toemail: email,
              username: fullname
        });

        //Lưu trữ dữ liệu 
        sessionStorage.setItem("email", email);
        sessionStorage.setItem("password", password);
        sessionStorage.setItem("fullname", fullname);
    
        if (response.status===200) {
          alert("Đăng ký thành công! Vui lòng kiểm tra email của bạn để xác minh.");
          navigate("/verifyregister");
        } else {
          alert("Lỗi đăng ký: " + response.message);
        }
      } catch (error) {
        console.error("Lỗi: " + error);
        alert("Đã có lỗi xảy ra khi đăng ký.");
      }
    } else {
      alert("Nhập lại mật khẩu không chính xác")
    }
  };
  return (
    <div className="register">
      <HeaderUser/>
      <div className="register-container">    
        <div className="registration-form">
          <h1>Đăng ký</h1>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="text"
            placeholder="Họ tên"
            value={fullname}
            onChange={(e) => setFullname(e.target.value)}
          />
          <input
            type="password"
            placeholder="Mật khẩu"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <input
            type="password"
            placeholder="Nhập lại mật khẩu"
            value={vpassword}
            onChange={(e) => setVpassword(e.target.value)}
          />
          <div className="flex-container">
            <button className="flex-item" onClick={handleRegister}>Đăng ký</button>
            <div className="flex-item">
                <a href="/login">Đã có tài khoản?</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;