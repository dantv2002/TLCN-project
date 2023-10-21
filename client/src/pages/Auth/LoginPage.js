import React, { useState} from "react";
import JSEncrypt from "jsencrypt";
import axios from "axios";
import { useNavigate, useLocation, redirect } from "react-router-dom";
import toast from "react-hot-toast";
import img_logo from "../../img/logo.png";
import img_login from "../../img/login/hopital.jpg";
import img_google from "../../img/login/google.png";
import { pub_key} from "../../security/JSEncryption";
import { useAuth } from "../../context/auth";
import '../../css/Login.css';
import Layout from "../../components/Layout/Layout";

const LoginPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [auth, setAuth] = useAuth();
    const navigate = useNavigate();
    const location = useLocation();

    // form function
    const handleSubmit = async (e) => {
        e.preventDefault();
        const encrypt = new JSEncrypt();
        encrypt.setPublicKey(pub_key);

        //  Data Encrypt
		let encryptedPassword = encrypt.encrypt(password);
        try {
            const res = await axios.post("http://localhost:8080/api/login",
                        {email, encryptedPassword});
            if (res.status(200)) {
              toast.success(res.data && res.message)
              setAuth({
                ...auth,
                user: res.data.fullname,
                email: res.data.email,
                token: res.data.token
              });
              localStorage.setItem("auth", JSON.stringify(res.data))
              redirect("/");
            } else {
                toast.error(res.message)
            }
        } catch (error) {
            console.log(error);
            toast.error("Error");
        }
    };
    return (
        <div className="login-page" style={{minHeight: "90vh"}}>
            <form onSubmit={handleSubmit}>
            <div className="login-page-container">
                <div className="img-login-container">
                    <img className="img-login" src={img_login} alt="Ảnh bệnh viện" />
                </div>
                <div className="form-container">
                    <img className="img-logo" src={img_logo} alt="Logo" />
                    <h1 className="title">Đăng nhập</h1>
                        <div className="mb-2">
                            <input
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                className="form-control"
                                type="text"
                                placeholder="Email của bạn"
                                required
                            />
                        </div>
                        <div className="mb-2">
                            <input
                                id="matkhau"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                className="form-control"
                                type="password"
                                placeholder="Mật khẩu"
                                required
                            />
                        </div>
                        <a href="/" className="forgot-password">Quên mật khẩu ?</a>
                        <div className="d-flex justify-content-center">
                            <button className="submit-btn">
                                Đăng nhập
                            </button>
                        </div>
                    <h3>Hoặc</h3>
                    <img className="img-google" src={img_google} alt="google" />
                </div>
            </div>
            </form>
        </div>
    )
};

export default LoginPage;