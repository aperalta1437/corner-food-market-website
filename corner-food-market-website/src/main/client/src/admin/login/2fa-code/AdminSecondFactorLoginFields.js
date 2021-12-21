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

    axios
      .post(
        "api/admin/login/tfa-post-authenticate",
        {
          password: firstFactorAuthentication.base64RsaEncryptedPassword,
          tfaCode: tfaCode,
        },
        {
          headers: {
            // "X-XSRF-TOKEN": Cookies.get("XSRF-TOKEN"),
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
          console.log(response.data["Access-Token"]);

          axios
            .post("api/admin/login/tfa-post-authenticate/logout", {
              headers: {
                Authorization: firstFactorAuthentication.accessToken,
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": window.location.origin
                  ? window.location.origin
                  : window.location.protocol +
                    "//" +
                    window.location.hostname +
                    (window.location.port ? ":" + window.location.port : ""), // This approach covers IE.
              },
            })
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
      <form onSubmit={submitSecondFactorLoginForm} onReset={resetSecondFactorLoginForm}>
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
