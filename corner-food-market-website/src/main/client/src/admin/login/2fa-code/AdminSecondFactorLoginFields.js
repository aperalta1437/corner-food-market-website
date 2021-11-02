import React, { useState } from "react";
import { Redirect, useHistory } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { setAuthentication } from "../../Global/adminAuthentication";
import { resetFirstFactorAuthentication } from "../../Global/adminFirstFactorAuthentication";
import axios from "axios";
import { StatusCodes } from "http-status-codes";
import { flipIsLoading } from "../../Global/adminHttpResponseLoaderGlobalState";

function AdminSecondFactorLoginFields({ fromRoute }) {
  const dispatch = useDispatch();
  const routerHistory = useHistory();
  const [tfaCode, setTfaCode] = useState("");

  const firstFactorAuthentication = useSelector(
    (state) => state.adminFirstFactorAuthentication.value
  );

  const submitSecondFactorLoginForm = (event) => {
    event.preventDefault();

    dispatch(flipIsLoading());

    const base64RsaEncryptedPassword = window.btoa(
      firstFactorAuthentication.rsaEncryptedPassword
    );

    axios
      .post(
        "http://localhost:8080/api/admin/login/tfa-post-authenticate",
        {
          email: firstFactorAuthentication.email,
          password: firstFactorAuthentication.rsaEncryptedPassword,
          tfaCode: tfaCode,
        },
        {
          headers: {
            Authorization: firstFactorAuthentication.accessToken,
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "http://localhost:3000",
          },
        }
      )
      .then((response) => {
        if (response.status === StatusCodes.OK) {
          dispatch(
            setAuthentication({
              isAuthenticated: true,
              accessToken: response.data["Access-Token"],
            })
          );

          axios
            .post(
              "http://localhost:8080/api/admin/login/tfa-post-authenticate/logout",
              {
                email: firstFactorAuthentication.email,
                password: firstFactorAuthentication.rsaEncryptedPassword,
                tfaCode: tfaCode,
              },
              {
                headers: {
                  Authorization: firstFactorAuthentication.accessToken,
                  "Content-Type": "application/json",
                  "Access-Control-Allow-Origin": "http://localhost:3000",
                },
              }
            )
            .then((response) => {
              if (response.status === StatusCodes.OK) {
                console.log("First factor logged out successfully.");
              }
            })
            .catch((error) => {
              console.log("Error: " + error);
            });

          dispatch(resetFirstFactorAuthentication());
        }

        if (fromRoute) {
          routerHistory.push(fromRoute);
        } else {
          routerHistory.push("/admin/account");
        }
        dispatch(flipIsLoading());
      })
      .catch((error) => {
        console.log("Error: " + error);
      });
  };

  const resetSecondFactorLoginForm = () => {
    setTfaCode("");
  };

  if (!firstFactorAuthentication.isAuthenticated) {
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
