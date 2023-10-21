import React from 'react';
import { NavLink, Link } from 'react-router-dom';
import { useAuth } from '../../context/auth';
import toast from 'react-hot-toast';

const Header = () => {
  const [auth, setAuth] = useAuth();
  const handleLogout = () => {
    setAuth({
      ...auth,
      user: null,
      email: null,
      token: ""
    });
    localStorage.removeItem("auth");
    toast.success("Logout Successfully");
  }
  return (
    <section id="header">
      <nav className="navbar navbar-expand-lg bg-body-tertiary fixed-top">
        <div className="container-fluid">
          <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarTogglerDemo01"
              aria-controls="navbarTogglerDemo01"
              aria-expanded="false"
              aria-label="Toggle navigation"
          >
              <span className="navbar-toggler-icon" />
          </button>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
              <Link to="/" className="navbar-brand">
                  <a href="/">
                      <img src="../img/logo.png" class="logo" alt="logo"></img> 
                  </a>
              </Link>
              <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                <li className="nav-item">
                    <NavLink to="/" className="nav-link ">
                    Home
                    </NavLink>
                </li>
                <li className="nav-item dropdown">
                    <Link
                      className="nav-link dropdown-toggle"
                      to={"/"}
                      data-bs-toggle="dropdown"
                    >
                    Giới thiệu
                    </Link>
                </li>
                <li className="nav-item dropdown">
                    <Link
                      className="nav-link dropdown-toggle"
                      to={"/"}
                      data-bs-toggle="dropdown"
                    >
                    Giới thiệu
                    </Link>
                </li>
                <li className="nav-item dropdown">
                    <Link
                      className="nav-link dropdown-toggle"
                      to={"/"}
                      data-bs-toggle="dropdown"
                    >
                    Đội ngũ bác sĩ
                    </Link>
                </li><li className="nav-item dropdown">
                    <Link
                      className="nav-link dropdown-toggle"
                      to={"/"}
                      data-bs-toggle="dropdown"
                    >
                    Dịch vụ
                    </Link>
                </li><li className="nav-item dropdown">
                    <Link
                      className="nav-link dropdown-toggle"
                      to={"/"}
                      data-bs-toggle="dropdown"
                    >
                    Liên hệ - hỏi đáp
                    </Link>
                </li>
                {!auth?.user ? (
                    <>
                    <li className="nav-item">
                        <NavLink to="/" className="nav-link">
                        Register
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        <NavLink to="/login" className="nav-link">
                        Login
                        </NavLink>
                    </li>
                    </>
                ) : (
                    <>
                    <li className="nav-item dropdown">
                        <NavLink
                        className="nav-link dropdown-toggle"
                        href="#"
                        role="button"
                        data-bs-toggle="dropdown"
                        style={{ border: "none" }}
                        >
                        {auth?.user}
                        </NavLink>
                        <ul className="dropdown-menu">
                        <li>
                            <NavLink
                            onClick={handleLogout}
                            to="/login"
                            className="dropdown-item"
                            >
                            Logout
                            </NavLink>
                        </li>
                        </ul>
                    </li>
                    </>
                )}
              </ul>
          </div>
        </div>
      </nav>
    </section>
  )
}

export default Header