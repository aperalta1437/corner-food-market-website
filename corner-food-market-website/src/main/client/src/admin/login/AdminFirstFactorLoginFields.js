import React, { useState } from "react";
import { setAuthentication } from "../Global/adminAuthentication";
import { setFirstFactorAuthentication } from "../Global/adminFirstFactorAuthentication";
import axios from "axios";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, Redirect } from "react-router";

function AdminFirstFactorLoginFields({ fromRoute }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const routerHistory = useHistory();
  const dispatch = useDispatch();

  const adminFirstFactorAuthentication = useSelector(
    (state) => state.adminFirstFactorAuthentication.value
  );

  const adminAuthentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  const submitFirstFactorLoginForm = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/api/admin/login/tfa-pre-authenticate", {
        email: email,
        password: password,
      })
      .then((response) => {
        if (response.status === 200) {
          if (
            response.data["Tfa-Expiration-Time-In-Milliseconds"] === undefined
          ) {
            dispatch(
              setAuthentication({
                isAuthenticated: true,
                accessToken: response.data["Access-Token"],
              })
            );
            console.log(adminAuthentication.isAuthenticated);
            console.log(adminAuthentication.accessToken);
          } else {
            dispatch(
              setFirstFactorAuthentication({
                isAuthenticated: true,
                accessToken: response.data["Access-Token"],
                email: email,
                password: password,
                rsaPublicKey: response.data["RSA-Public-Key"],
              })
            );
            routerHistory.push("/admin/login/tfa-code");
            // console.log(adminFirstFactorAuthentication);
            // console.log("Hello: " + adminFirstFactorAuthentication.accessToken);
            // console.log("Ho: " + response.data["Access-Token"]);
          }

          routerHistory.push(fromRoute ? fromRoute : "/admin/account");
        }
      })
      .catch((error) => {
        console.log("Error: " + error);
      });
  };

  const resetLoginForm = () => {
    setEmail("");
    setPassword("");
  };

  if (adminFirstFactorAuthentication.isAuthenticated) {
    return <Redirect to="/admin/login/2fa-code" />;
  } else {
    return (
      <form onReset={resetLoginForm} onSubmit={submitFirstFactorLoginForm}>
        <div className="mb-3">
          <input
            className="form-control"
            placeholder="Email"
            name="email"
            // autoComplete="off"
            type="text"
            onChange={(event) => {
              setEmail(event.target.value);
            }}
            required
          />
        </div>
        <div className="mb-3">
          <input
            className="form-control"
            placeholder="Password"
            name="password"
            type="password"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
            required
          />
        </div>
        <div className="mb-3">
          <a href="#" className="float-end">
            Forgot password?
          </a>
          <label className="form-check">
            <input type="checkbox" class="form-check-input" checked="" />
            <span className="form-check-label">Remember</span>
          </label>
        </div>
        <div className="mb-4">
          <button type="submit" className="btn btn-primary w-100">
            Log In
          </button>
        </div>
        <p className="text-center mb-4">
          Don't have account? <a href="#">Sign up</a>
        </p>
      </form>
    );
  }
}

export default AdminFirstFactorLoginFields;
