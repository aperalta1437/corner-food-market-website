import React, { useState } from "react";
import { setAuthentication } from "../Global/adminAuthentication";
import { setFirstFactorAuthentication } from "../Global/adminFirstFactorAuthentication";
import { flipIsLoading } from "../Global/adminHttpResponseLoaderGlobalState";
import axios from "axios";
import { StatusCodes } from "http-status-codes";
import { useDispatch, useSelector } from "react-redux";
import { useHistory, Redirect } from "react-router";
import { AdminLoginProcessIssueEnum } from "./Utils/adminLoginProcessIssueEnum";

function AdminFirstFactorLoginFields({ fromRoute }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const routerHistory = useHistory();
  const dispatch = useDispatch();

  const adminFirstFactorAuthentication = useSelector(
    (state) => state.adminFirstFactorAuthentication.value
  );

  const authentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  const httpResponseLoaderGlobalState = useSelector(
    (state) => state.adminHttpResponseLoaderGlobalState.value
  );

  const submitFirstFactorLoginForm = (event) => {
    event.preventDefault();

    dispatch(flipIsLoading());

    axios
      .post("http://localhost:8080/api/admin/login/authenticate", {
        email: email,
        password: password,
      })
      .then((response) => {
        if (response.status === StatusCodes.OK) {
          if (response.data["Is-Tfa-Enabled"]) {
            console.log(
              "Base64 public key: " + response.data["Base64-Rsa-Public-Key"]
            );
            dispatch(
              setFirstFactorAuthentication({
                isAuthenticated: true,
                accessToken: response.data["Access-Token"],
                email: email,
                password: password,
                base64RsaPublicKey: response.data["Base64-Rsa-Public-Key"],
              })
            );
            routerHistory.push("/admin/login/tfa-code");
            // console.log(adminFirstFactorAuthentication);
            // console.log("Hello: " + adminFirstFactorAuthentication.accessToken);
            // console.log("Ho: " + response.data["Access-Token"]);
          } else {
            dispatch(
              setAuthentication({
                isAuthenticated: true,
                accessToken: response.data["Access-Token"],
              })
            );
            console.log(authentication.isAuthenticated);
            console.log(authentication.accessToken);
          }

          routerHistory.push(fromRoute ? fromRoute : "/admin/account");
        } else if ((response.status = StatusCodes.UNAUTHORIZED)) {
          routerHistory.push(
            fromRoute
              ? fromRoute
              : `/admin/login?issue=${AdminLoginProcessIssueEnum.FAILED_LOGIN.name}`
          );
        } else if ((response.status = StatusCodes.INTERNAL_SERVER_ERROR)) {
        }

        dispatch(flipIsLoading());
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
        </div>
        <div className="mb-4">
          <button type="submit" className="btn btn-primary mt-3 w-100">
            Log In
          </button>
        </div>
      </form>
    );
  }
}

export default AdminFirstFactorLoginFields;
