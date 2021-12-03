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
                alt="Corner Food Market logo"
              />
            </a>
          </div>
          <div className="col-nav">
            <ul className="nav">
              <li className="nav-item">
                <a
                  className="nav-link btn-icon"
                  onClick={(e) => window.darkmode(e.target)}
                  title="Dark mode"
                  href="#"
                >
                  <i className="material-icons md-nights_stay"></i>
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">
                  {" "}
                  English{" "}
                </a>
              </li>
            </ul>
          </div>
        </div>
      </header>
    </>
  );
}

export default AdminHeader;
