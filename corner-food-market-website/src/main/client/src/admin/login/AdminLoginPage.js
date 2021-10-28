import React from "react";
import AdminHeader from "./AdminHeader";
import AdminLoginForm from "./AdminLoginForm";
import { Switch, Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";

function AdminLoginPage() {
  // const location = useLocation();
  // const fromRoute =
  //   location.state !== undefined && location.state.fromRoute !== undefined
  //     ? location.state.fromRoute
  //     : null;

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
          <Switch>
            <Route
              path="/admin/login"
              component={(props) => <AdminLoginForm />}
            />
          </Switch>
        </section>
      </>
    );
  }
}

export default AdminLoginPage;
