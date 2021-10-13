import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { setAuthentication } from "../Global/AdminAuthentication";
import axios from "axios";

const AdminLoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const dispatch = useDispatch();

  const submitLoginForm = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/admin/login/authenticate", {
        email: email,
        password: password,
      })
      .then((response) => {
        if (response.status === 200) {
          console.log(response.data.token);
          dispatch(
            setAuthentication({
              isAuthenticated: true,
              accessToken: response.data.token,
            })
          );
        }
      });
  };

  const resetLoginForm = () => {
    setEmail("");
    setPassword("");
  };

  return (
    <form onReset={resetLoginForm} onSubmit={submitLoginForm}>
      <div className="mb-3">
        <input
          className="form-control"
          placeholder="Email"
          name="email"
          autoComplete="off"
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
          Login
        </button>
      </div>
    </form>
  );
};

export default AdminLoginForm;
