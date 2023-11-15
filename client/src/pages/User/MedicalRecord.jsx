import React, { useState, useEffect } from 'react';
import axios from 'axios';
import HeaderUser from '../../components/Layout/HeaderUser';
import { readMedicalRecordApi } from '../../utils/api/user';
import ReactPaginate from 'react-paginate';
import { useNavigate } from 'react-router-dom';
import moment from 'moment/moment';

const MedicalRecord = () => {
    const [medicalRecords, setMedicalRecords] = useState([]);
    const [totalpage, setTotalpage] = useState(0);
    const [currentpage, setCurrentpage] = useState(0);
    const token = localStorage.getItem("token");
    const navigate = useNavigate();
    useEffect(() => {
        const fetchMedicalRecords = async () => {
        try {
            const response = await axios.get(readMedicalRecordApi(currentpage),{
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setMedicalRecords(response.data.data.medicals);
            setTotalpage(Math.ceil(response.data.data.total / 5));
        } catch (error) {
            console.error('Lỗi khi lấy dữ liệu bệnh án', error);
        }
        };
        fetchMedicalRecords();
    }, [token, currentpage]);
    const readMedicalRecordDetail = (id) => {
        navigate(`/user/medicalrecord/detail/${id}`)
    }
    const handlePageClick = (data) => {
        const selectedPage = data.selected;
        setCurrentpage(selectedPage);
    }
    return (
        <div>
            <HeaderUser/>
            <div className='medicalrecord_container'>
                <h1>Hồ sơ bệnh án</h1>
                <table>
                    <thead>
                    <tr>
                        <th>Số thứ tự</th>
                        <th>Ngày khám</th>
                        <th>Tên bác sĩ</th>
                        <th>Tùy chọn</th>
                    </tr>
                    </thead>
                    <tbody>
                    {medicalRecords.map((record, index) => (
                        <tr key={record.id}>
                        <td>{index+1 + currentpage*5}</td>
                        <td>{moment(record.date).format("DD/MM/YYYY")}.</td>
                        <td>{record.nameDoctor}</td>
                        <td>
                            <button onClick={() => readMedicalRecordDetail(record.id)}>Xem</button>
                        </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <ReactPaginate
                    nextLabel="Sau >"
                    onPageChange={handlePageClick}
                    pageRangeDisplayed={3}
                    marginPagesDisplayed={2}
                    pageCount={totalpage}
                    previousLabel="< Trước"
                    pageClassName="page-item"
                    pageLinkClassName="page-link"
                    previousClassName="page-item"
                    previousLinkClassName="page-link"
                    nextClassName="page-item"
                    nextLinkClassName="page-link"
                    breakLabel="..."
                    breakClassName="page-item"
                    breakLinkClassName="page-link"
                    containerClassName="pagination"
                    activeClassName="active"
                    renderOnZeroPageCount={null}
                />
            </div>
        </div>
    );
};

export default MedicalRecord;
