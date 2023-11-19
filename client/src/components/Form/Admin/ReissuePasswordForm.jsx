import React, {useState} from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { reissuePassApi } from "../../../utils/api/admin";
import HeaderAdminDoctor from "../../Layout/HeaderAdminDoctor";
import AdminMenu from "../../Layout/AdminMenu";
import "../../../css/Admin.css";

const ReissuePasswordForm = () => {

    const [password, setPassword] = useState("");
    const [confirmpassword, setConfirmpassword] = useState("");
    const token = localStorage.getItem("token");
    const {id} = useParams();
    const navigate = useNavigate();

    const handleRePassword = async(e) => {
        e.preventDefault();
        if (password === confirmpassword) {
            try {
                const reponse = await axios.put(reissuePassApi(id),
                {
                    password,
                },{
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                if (reponse.status === 200) {
                    alert("Cấp lại mật khẩu thành công");
                    navigate("/admin/dashboard/account");
                }
            } catch(error){
                console.log(error);
                alert("Cấp lại mật khẩu không thành công");
            }
        } else {
            alert("Nhập lại mật khẩu sai")
        }
        
    }

    return (
        <div>
            <HeaderAdminDoctor/>
            <div className='container-fluid'>
                <div className='row'>
                    <div className='col-md-3'>
                    <AdminMenu/>
                    </div>
                    <div className='col-md-9'>
                        <div className="repass_container">
                        <form onSubmit={handleRePassword}>
                            <h1>Cấp lại mât khẩu cho id: {id}</h1>
                            <div className="mb-3">
                                <input
                                    type="password"
                                    className="form-control"
                                    placeholder="Mật khẩu mới"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                            </div>
                            <div className="mb-3">
                                <input
                                    type="password"
                                    className="form-control"
                                    placeholder="Nhập lại mật khẩu"
                                    value={confirmpassword}
                                    onChange={(e) => setConfirmpassword(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="btn btn-primary">
                                Xác nhận
                            </button>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReissuePasswordForm;