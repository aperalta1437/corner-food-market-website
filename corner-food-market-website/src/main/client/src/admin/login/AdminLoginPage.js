import React from "react";
import AdminHeader from "./AdminHeader";
import AdminLoginForm from "./AdminLoginForm";
import { Switch, Route, Redirect, useLocation } from "react-router-dom";
import { useSelector } from "react-redux";
import { LoginProcessIssueEnum } from "./Utils/loginProcessIssueEnum";

function AdminLoginPage() {
  const issue = new URLSearchParams(useLocation().search).get("issue");
  const loginIssueMessage = issue ? LoginProcessIssueEnum[issue].message : null;
  const location = useLocation();
  const fromRoute =
    location.state !== undefined && location.state.fromRoute !== undefined
      ? location.state.fromRoute
      : null;

  const adminAuthentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  if (adminAuthentication.isAuthenticated) {
    return <Redirect to="/admin/account" />;
  } else {
    return (
      <>
        <AdminHeader />
        <section className="content-main">
          <div
            id="login"
            className="card shadow mx-auto"
            style={{ maxWidth: "380px", marginTop: "100px" }}
          >
            <div className="card-body">
              <h4 className="card-title mb-4">Log in</h4>
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
                    <AdminLoginForm fromRoute={fromRoute} />
                  )}
                />
              </Switch>

              <p className="text-center mb-4">
                Don't have account? <a href="#">Sign up</a>
              </p>
            </div>
          </div>
        </section>
      </>
    );
  }
}

export default AdminLoginPage;
