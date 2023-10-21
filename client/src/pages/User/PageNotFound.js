import React from 'react';
//import Layout from '../components/Layout/Layout'

const Pagenotfound = () => {
  return (
    //<Layout title={"Không tìm thấy trang này"}>
      <div className="container404">
        <div className="containernotfound">
          <h2>Lỗi! Không tìm thấy trang này</h2>
          <h1>404</h1>
          <p>Chúng tôi không thể tìm thấy trang mà bạn đang tìm kiếm</p>
          <a href="/">Trở về trang chủ</a>
        </div>
      </div>
    //</Layout>
  )
}

export default Pagenotfound