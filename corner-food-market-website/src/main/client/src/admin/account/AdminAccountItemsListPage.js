import React from "react";
import { useState, useEffect, useCallback } from "react";
import AdminAccountItem from "./AdminAccountItem";
import axios from "axios";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { resetAuthentication } from "../Global/adminAuthentication";
import { LoginProcessIssueEnum } from "../login/Utils/loginProcessIssueEnum";
import { StatusCodes } from "http-status-codes";
import Cookies from "js-cookie";

function AdminAccountItemsList() {
  const routerHistory = useHistory();
  const dispatch = useDispatch();

  const adminAuthentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  console.log(adminAuthentication.accessToken);
  let [itemsList, setItemsList] = useState([]);

  const fetchItemsList = useCallback(() => {
    axios
      .get("http://localhost:8080/api/admin/account/", {
        headers: {
          Authorization: adminAuthentication.accessToken,
        },
      })
      .then((response) => {
        console.log(adminAuthentication.accessToken);
        setItemsList(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        if (error.response.status == 401) {
          dispatch(resetAuthentication());
          routerHistory.push(
            "/admin/login?issue=" + LoginProcessIssueEnum.EXPIRED_SESSION.name
          );
        }
        console.log("Error: " + error);
      });
  }, []);

  useEffect(() => {
    fetchItemsList();
  }, [fetchItemsList]);

  const onRemoveItem = (itemId) => {
    axios.interceptors.request.use(
      function (config) {
        // Do something before request is sent
        console.log(config);
        return config;
      },
      function (error) {
        // Do something with request error
        return Promise.reject(error);
      }
    );

    axios
      .post(
        "http://localhost:8080/api/admin/account/remove-item/" + itemId,
        {},
        {
          headers: {
            Authorization: adminAuthentication.accessToken,
            "X-XSRF-TOKEN": Cookies.get("XSRF-TOKEN"),
            // 'Content-Type': 'application/json',
            "Access-Control-Allow-Origin": "http://localhost:3000",
          },
        }
      )
      .then((response) => {
        if (response.status === StatusCodes.OK) {
          console.log(response);
          setItemsList(itemsList.filter((item) => item.id != itemId));
        }
      })
      .catch((error) => {
        console.log(error.response);
        if (error.response.status === StatusCodes.UNAUTHORIZED) {
          // dispatch(resetAuthentication());
          // routerHistory.push("/admin/login");
        }
        console.log("Error: " + error);
      });
  };

  return (
    <section className="content-main">
      <div className="content-header">
        <h2 classNameName="content-title">Products grid</h2>
        <div>
          <a href="#" className="btn btn-light">
            Export
          </a>
          <a href="#" className="btn btn-light">
            Import
          </a>
          <a href="#" className="btn btn-primary">
            Create new
          </a>
        </div>
      </div>

      <div className="card mb-4">
        <header className="card-header">
          <div className="row gx-3">
            <div className="col-lg-4 col-md-6 me-auto">
              <input
                type="text"
                placeholder="Search..."
                className="form-control"
              />
            </div>
            <div className="col-lg-2 col-6 col-md-3">
              <select className="form-select">
                <option>All category</option>
                <option>Electronics</option>
                <option>Clothings</option>
                <option>Something else</option>
              </select>
            </div>
            <div className="col-lg-2 col-6 col-md-3">
              <select className="form-select">
                <option>Latest added</option>
                <option>Cheap first</option>
                <option>Most viewed</option>
              </select>
            </div>
          </div>
        </header>
        <div className="card-body">
          <div className="row gx-3 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 row-cols-xxl-5">
            {itemsList.map((item) => (
              <AdminAccountItem
                key={item.id}
                item={item}
                onRemoveItem={onRemoveItem}
              />
            ))}
          </div>

          <nav className="float-end mt-4" aria-label="Page navigation">
            <ul className="pagination">
              <li className="page-item disabled">
                <a className="page-link" href="#">
                  Previous
                </a>
              </li>
              <li className="page-item active">
                <a className="page-link" href="#">
                  1
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  2
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  3
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  Next
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
  );
}

export default AdminAccountItemsList;
