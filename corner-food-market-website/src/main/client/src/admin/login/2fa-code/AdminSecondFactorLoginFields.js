import React, { useState } from "react";
import { Redirect, useHistory } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { setAuthentication } from "../../Global/adminAuthentication";
import axios from "axios";

function AdminSecondFactorLoginFields({ fromRoute }) {
  const dispatch = useDispatch();
  const routerHistory = useHistory();
  const [tfaCode, setTfaCode] = useState("");

  const adminFirstFactorAuthentication = useSelector(
    (state) => state.adminFirstFactorAuthentication.value
  );

  const submitSecondFactorLoginForm = (event) => {
    event.preventDefault();
    axios
      .post(
        "http://localhost:8080/api/admin/login/tfa-post-authenticate/" +
          tfaCode,
        // {
        //   tfaCode: tfaCode,
        // },
        {
          headers: {
            Authorization: adminFirstFactorAuthentication.accessToken,
            // 'Content-Type': 'application/json',
            // 'Access-Control-Allow-Origin': 'http://localhost:3000',
          },
        }
      )
      .then((response) => {
        if (response.status === 200) {
          dispatch(
            setAuthentication({
              isAuthenticated: true,
              accessToken: response.data["Access-Token"],
            })
          );
        }

        if (fromRoute) {
          routerHistory.push(fromRoute);
        }
      })
      .catch((error) => {
        console.log("Error: " + error);
      });
  };

  const resetSecondFactorLoginForm = () => {
    setTfaCode("");
  };

  if (!adminFirstFactorAuthentication.isAuthenticated) {
    return <Redirect to="/admin/login" />;
  } else {
    return (
      <form
        onSubmit={submitSecondFactorLoginForm}
        onReset={resetSecondFactorLoginForm}
      >
        <div className="mb-3">
          <input
            className="form-control"
            placeholder="Enter Security Code"
            name="tfaCode"
            autoComplete="off"
            type="text"
            onChange={(event) => {
              setTfaCode(event.target.value);
            }}
            required
          />
        </div>
        <div className="mb-4">
          <button type="submit" className="btn btn-primary w-100">
            Confirm
          </button>
        </div>
      </form>
    );
  }
}

export default AdminSecondFactorLoginFields;
