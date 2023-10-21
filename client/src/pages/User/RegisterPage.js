import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import JSEncrypt from "jsencrypt";
import { pub_key } from "../../security/JSEncryption";

const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [vpassword, setVpassword] = useState("");
  const [fullname, setFullname] = useState("");
  const [birthday, setBirthday] = useState("");
  const [address, setAddress] = useState("");
  const [phonenumber, setPhonenumber] = useState("");

  const navigate = useNavigate()
  const handleRegister = async () => {
    if (password===vpassword){
      try {
        const encrypt = new JSEncrypt();
        encrypt.getPublicKey(pub_key);

        const passwordEncrypt = encrypt.encrypt(password);

        const response = await axios.post("http://localhost:8080/api/sendEmailSignUp", {
              toemail: email,
              username: fullname
        });
        //Lưu trữ dữ liệu 
        sessionStorage.setItem("email", email);
        sessionStorage.setItem("password", passwordEncrypt);
        sessionStorage.setItem("fullname", fullname);
        sessionStorage.setItem("birthday", birthday);
        sessionStorage.setItem("address", address);
        sessionStorage.setItem("phonenumber", phonenumber);
    
        if (response.status===200) {
          alert("Đăng ký thành công! Vui lòng kiểm tra email của bạn để xác minh.");
          navigate("/verify");
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
    <div>
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
        type="date"
        placeholder="Ngày sinh"
        value={birthday}
        onChange={(e) => setBirthday(e.target.value)}
      />
      <input
        type="text"
        placeholder="Địa chỉ"
        value={address}
        onChange={(e) => setAddress(e.target.value)}
      />
      <input
        type="text"
        placeholder="Số điện thoại"
        value={phonenumber}
        onChange={(e) => setPhonenumber(e.target.value)}
      />
      <input
        type="=password"
        placeholder="Mật khẩu"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <input
        type="=password"
        placeholder="Nhập lại mật khẩu"
        value={vpassword}
        onChange={(e) => setVpassword(e.target.value)}
      />
      <button onClick={handleRegister}>Register</button>
    </div>
  );
};

export default RegisterPage;