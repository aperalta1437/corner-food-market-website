import React from "react";
import { Link } from "react-router-dom";
import logo from "../../images/logo.png";

function AdminAccountAsideMenu() {
  return (
    <aside className="navbar-aside" id="offcanvas_aside">
      <div className="aside-top">
        <a href="/admin/account/" className="brand-wrap">
          <img src={logo} height="60" className="img-fluid" alt="Corner Food Market logo" />
        </a>
        <div>
          <button className="btn btn-icon btn-aside-minimize">
            {" "}
            <i className="text-muted material-icons md-menu_open"></i>{" "}
          </button>
        </div>
      </div>
      {/* aside-top */}

      <nav>
        <ul className="menu-aside">
          <li className="menu-item">
            <Link className="menu-link" to="/admin/account/">
              {" "}
              <i class="icon material-icons md-shopping_bag"></i>
              <span className="text">Items</span>
            </Link>
          </li>
          <li className="menu-item">
            <Link className="menu-link" to="/admin/account/add-item">
              {" "}
              <i className="icon material-icons md-add_box"></i>
              <span className="text">Add Item</span>
            </Link>
          </li>
          <li className="menu-item has-submenu">
            <a className="menu-link" href="#">
              {" "}
              <i className="icon material-icons md-person"></i>
              <span className="text">Account</span>
            </a>
            <div className="submenu">
              <Link to="/admin/account/create-new-admin">
                {" "}
                <span className="text">Create New Admin</span>
              </Link>
            </div>
          </li>
        </ul>
        <br />
        <br />
      </nav>
    </aside>
  );
}

export default AdminAccountAsideMenu;
