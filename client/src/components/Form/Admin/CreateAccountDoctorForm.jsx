import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import moment from 'moment';
import { createAccountDoctorApi } from '../../../utils/api/admin';
import AdminMenu from '../../Layout/AdminMenu';
import HeaderAdminDoctor from '../../Layout/HeaderAdminDoctor';
import axios from 'axios';
import "../../../css/Admin.css"


const CreateAccountDoctorForm = () => {
    
    const [fullname, setFullname] = useState("");
    const [birthday, setBirthday] = useState("");
    const [gender, setGender] = useState(true);
    const [address, setAddress] = useState("");
    const [phonenumber, setPhonenumber] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [identificationCard, setIdentificationCard] = useState("");
    const [department, setDepartment] = useState("");
    const [title, setTitle] = useState("");
    const token = localStorage.getItem("token");

    const navigate = useNavigate();

    const handleCreateAccountDoctor = async(e) => {
        e.preventDefault();

        const birthdayFormatted = moment(birthday).format("MM/DD/YYYY");
        try {
            const reponse = await axios.post(createAccountDoctorApi, 
            {
                fullname, 
                birthday: birthdayFormatted, 
                gender, 
                address, 
                phonenumber,
                email,
                password,
                identificationCard, 
                department, 
                title,
            },{
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (reponse.status === 201) {
                alert("Tạo tài khoản thành công");
                navigate("/admin/dashboard/account");
            }
        } catch(error){
            console.log(error);
            alert("Tạo tài khoản không thành công");
        }
        
    }

    return (
        <>
        <div>
            <HeaderAdminDoctor/>
            <div className='container-fluid'>
                <div className='row'>
                    <div className='col-md-3'>
                        <AdminMenu/>
                    </div>
                    <div className='col-md-9'>
                        <div className="create_account_doctor">
                            <form onSubmit={handleCreateAccountDoctor}>
                                <h1>Tạo tài khoản bác sĩ</h1>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Họ tên"
                                        value={fullname}
                                        onChange={(e) => setFullname(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="date"
                                        className="form-control"
                                        placeholder="Ngày sinh"
                                        value={birthday}
                                        onChange={(e) => setBirthday(e.target.value)}
                                    />
                                </div>
                                <div className="gender">
                                    <label>Giới tính</label>
                                    <select
                                        className="form-control"
                                        value={gender ? "true" : "false"}
                                        onChange={(e) => setGender(e.target.value === "true")}
                                    >
                                        <option value="true">Nam</option>
                                        <option value="false">Nữ</option>
                                    </select>
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Địa chỉ"
                                        value={address}
                                        onChange={(e) => setAddress(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Số điện thoại"
                                        value={phonenumber}
                                        onChange={(e) => setPhonenumber(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Email"
                                        value={email}
                                        onChange={(e) => setEmail(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="password"
                                        className="form-control"
                                        placeholder="Mật khẩu"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="CCCD/CMND"
                                        value={identificationCard}
                                        onChange={(e) => setIdentificationCard(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Khoa"
                                        value={department}
                                        onChange={(e) => setDepartment(e.target.value)}
                                    />
                                </div>
                                <div className="mb-3">
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Chức danh"
                                        value={title}
                                        onChange={(e) => setTitle(e.target.value)}
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">
                                    Tạo tài khoản
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </>
    );
}

export default CreateAccountDoctorForm