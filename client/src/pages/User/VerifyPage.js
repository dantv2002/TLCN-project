import React, { useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const VerifyPage = () => {
    //Lấy dữ liệu
    const email = sessionStorage.getItem("email");
    const password = sessionStorage.getItem("password");
    const fullname = sessionStorage.getItem("fullname");
    const birthday = sessionStorage.getItem("birthday");
    const address = sessionStorage.getItem("address");
    const phonenumber = sessionStorage.getItem("phonenumber");
    const role = "PATIENT";

    const [verifycode, setVerifycode] = useState("");
    const navigate = useNavigate();
    const handleVerify = async() =>{
      try{
        const reponse = await axios.post("http://localhost:8080/api/signup" ,
            {email, password, role, fullname, birthday, address, phonenumber, verifycode});
        if(reponse.status===200){
          alert("Đăng ký thành công");
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
    <div>
      <input
        type="text"
        placeholder="Code"
        value={verifycode}
        onChange={(e) => setVerifycode(e.target.value)}
      />
      <button onClick={handleVerify}>Xác nhận</button>
    </div>
  )
}

export default VerifyPage