import React from "react";
import { Link } from "react-router-dom";
import logo from "./images/logo.png";

function Header() {
  return (
    <header className="section-header">
      <nav className="navbar navbar-dark navbar-expand p-0 bg-primary">
        <div className="container">
          <ul className="navbar-nav d-none d-md-flex mr-auto">
            <li className="nav-item">
              <a className="nav-link" href="/">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Delivery
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Payment
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/reviews">
                Reviews
              </a>
            </li>
          </ul>
          <ul className="navbar-nav">
            <li className="nav-item">
              <a href="#" className="nav-link">
                {" "}
                Call: +99812345678{" "}
              </a>
            </li>
            <li className="nav-item dropdown">
              <a
                href="#"
                className="nav-link dropdown-toggle"
                data-toggle="dropdown"
              >
                {" "}
                English{" "}
              </a>
              <ul
                className="dropdown-menu dropdown-menu-right"
                style={{ maxWidth: "100px" }}
              >
                <li>
                  <a className="dropdown-item" href="#">
                    Arabic
                  </a>
                </li>
                <li>
                  <a className="dropdown-item" href="#">
                    Russian{" "}
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
      <section className="main-header border-bottom">
        <div className="container" id="upper-header">
          <div className="row align-items-center">
            <div className="col-lg-3 col-4" id="div-logo">
              <Link to="/" className="brand-wrap">
                <img className="img-fluid" src={logo} />
              </Link>
            </div>
            <div className="col-lg-5 col-sm-12" id="div-search-bar">
              <form action="#" className="search">
                <div className="input-group w-100">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Search item..."
                  />
                  <div className="input-group-append">
                    <button className="btn btn-primary" type="submit">
                      <i className="fa fa-search"></i> Search
                    </button>
                  </div>
                </div>
              </form>
            </div>
            <div className="col-lg-4 col-sm-6 col-12">
              <div className="widgets-wrap float-md-right">
                <div className="widget-header icontext">
                  <div className="text">
                    <span className="text-muted">Welcome!</span>
                    <div>
                      {window.location.pathname.includes("/login") ? (
                        <>
                          <Link to="/">Home</Link> |{" "}
                          <Link to="/signup">Sign Up</Link>
                        </>
                      ) : window.location.pathname.includes("/signup") ? (
                        <>
                          <Link to="/">Home</Link> |{" "}
                          <Link to="/login">Log In</Link>
                        </>
                      ) : (
                        <>
                          <Link to="/login">Log In</Link> |{" "}
                          <Link to="/signup">Sign Up</Link>
                        </>
                      )}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </header>
  );
}

export default Header;
