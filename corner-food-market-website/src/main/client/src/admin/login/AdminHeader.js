import React from "react";
import logo from "../../images/logo.png";


function AdminHeader() {
  return (
    <>
      <header className="main-header navbar">
        <div className="container">
          <div className="col-lg-3 col-md-4 col-6">
            <a href="page-index-1.html" className="brand-wrap">
              <img
                src={logo}
                height="46"
                className="img-fluid"
                alt="Ecommerce dashboard template"
              />
            </a>
          </div>
          <div className="col-nav">
            <button
              className="btn btn-icon btn-mobile me-auto"
              data-trigger="#offcanvas_aside"
            >
              <i className="md-28 material-icons md-menu"></i>
            </button>
            <ul className="nav">
              <li className="nav-item">
                <a
                  className="nav-link btn-icon"
                  onclick="darkmode(this)"
                  title="Dark mode"
                  href="#"
                >
                  <i className="material-icons md-nights_stay"></i>
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link btn-icon" href="#">
                  <i class="material-icons md-notifications_active"></i>
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  {" "}
                  English{" "}
                </a>
              </li>
              <li className="dropdown nav-item">
                <a
                  className="dropdown-toggle"
                  data-bs-toggle="dropdown"
                  href="#"
                >
                  <img
                    className="img-xs rounded-circle"
                    src="images/people/avatar1.jpg"
                    alt="User"
                  />
                </a>
                <div className="dropdown-menu dropdown-menu-end">
                  <a className="dropdown-item" href="#">
                    My profile
                  </a>
                  <a className="dropdown-item" href="#">
                    Settings
                  </a>
                  <a className="dropdown-item text-danger" href="#">
                    Exit
                  </a>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </header>
    </>
  );
}

export default AdminHeader;
