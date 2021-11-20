import React from "react";

function AdminAccountHeader() {
  return (
    <header className="main-header navbar">
      <div className="col-search">
        <form className="searchform">
          <div className="input-group">
            <input
              list="search_terms"
              type="text"
              className="form-control"
              placeholder="Search term"
            />
            <button className="btn btn-light bg" type="button">
              {" "}
              <i className="material-icons md-search"></i>{" "}
            </button>
          </div>
          <datalist id="search_terms">
            <option value="Products" />
            <option value="New orders" />
            <option value="Apple iphone" />
            <option value="Ahmed Hassan" />
          </datalist>
        </form>
      </div>
      <div className="col-nav">
        <button
          className="btn btn-icon btn-mobile me-auto"
          data-trigger="#offcanvas_aside"
        >
          {" "}
          <i className="md-28 material-icons md-menu"></i>{" "}
        </button>
        <ul className="nav">
          <li className="nav-item">
            <a
              className="nav-link btn-icon"
              onClick={(e) => window.darkmode(e.target)}
              title="Dark mode"
              href="#"
            >
              {" "}
              <i className="material-icons md-nights_stay"></i>{" "}
            </a>
          </li>
          <li class="nav-item">
            <a className="nav-link" href="#">
              {" "}
              English{" "}
            </a>
          </li>
          <li className="dropdown nav-item">
            <a className="dropdown-toggle" data-bs-toggle="dropdown" href="#">
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
    </header>
  );
}

export default AdminAccountHeader;
