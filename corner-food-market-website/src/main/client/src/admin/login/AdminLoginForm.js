import React from "react";
import { useLocation, Switch, Route } from "react-router";
import AdminFirstFactorLoginFields from "./AdminFirstFactorLoginFields";
import AdminSecondFactorLoginFields from "./2fa-code/AdminSecondFactorLoginFields";
import { AdminLoginProcessIssueEnum } from "./Utils/adminLoginProcessIssueEnum";

const AdminLoginForm = () => {
  const location = useLocation();
  const fromRoute =
    location.state !== undefined && location.state.fromRoute !== undefined
      ? location.state.fromRoute
      : null;

  const issue = new URLSearchParams(useLocation().search).get("issue");
  const loginIssueMessage = issue
    ? AdminLoginProcessIssueEnum[issue].message
    : null;

  return (
    <div
      id="login"
      className="card shadow mx-auto"
      style={{ maxWidth: "380px", marginTop: "100px" }}
    >
      <div className="card-body">
        <h4 className="card-title mb-4">Log In</h4>
        {loginIssueMessage ? (
          <div className="alert alert-danger" role="alert">
            {loginIssueMessage}
          </div>
        ) : null}
        <Switch>
          <Route
            path="/admin/login"
            exact
            component={(props) => (
              <AdminFirstFactorLoginFields fromRoute={fromRoute} />
            )}
          />
          <Route
            path="/admin/login/2fa-code"
            exact
            component={(props) => (
              <AdminSecondFactorLoginFields fromRoute={fromRoute} />
            )}
          />
        </Switch>
      </div>
    </div>
  );
};

export default AdminLoginForm;
