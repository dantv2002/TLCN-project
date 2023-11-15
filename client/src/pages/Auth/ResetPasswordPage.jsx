import React, { useState } from "react";
import axios from "axios";
import { resetpassApi } from "../../utils/api/auth";
import { useNavigate } from "react-router-dom";
import "../../css/Auth.css"
import HeaderUser from "../../components/Layout/HeaderUser";

const ResetPasswordPage = () => {
  const [email, setEmail] = useState("");

  const navigate = useNavigate()
  const handleResetPassword = async () => {
    try {
        const response = await axios.post(resetpassApi, 
                        {email});
        //Lưu trữ dữ liệu
        sessionStorage.setItem("email", email);

        if (response.status===200) {
            alert("Email tồn tại! Vui lòng kiểm tra email của bạn để xác minh.");
            navigate("/verifyresetpassword");
        } else {
            alert("Email không tồn tại ");
        }
    } catch (error) {
    console.error("Lỗi: " + error);
    alert("Đã có lỗi xảy ra khi reset password.");
    }
  };
  return (
    <div className="reset">
      <HeaderUser/>
      <div className="reset-container">
        <div className="reset-form">
          <h1>Đặt lại mật khẩu</h1>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <div className="flex-container">
            <button className="flex-item" onClick={handleResetPassword}>Gửi mã</button>
            <div className="flex-item">
                <a href="/login">Trở về đăng nhập</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ResetPasswordPage;

