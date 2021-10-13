import React from "react";
import AdminHeader from "./AdminHeader";
import AdminLoginForm from "./AdminLoginForm";
import { Switch, Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";

function AdminLoginPage() {
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
              <h4 className="card-title mb-4">Sign in</h4>
              <Switch>
                <Route path="/admin/login" exact component={AdminLoginForm} />
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
