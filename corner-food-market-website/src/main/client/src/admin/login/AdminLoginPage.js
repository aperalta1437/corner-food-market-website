import React from "react";
import AdminHeader from "./AdminHeader";
import AdminLoginForm from "./AdminLoginForm";
import { Switch, Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import HttpResponseLoader from "../Utils/HttpResponseLoader";

function AdminLoginPage() {
  // const location = useLocation();
  // const fromRoute =
  //   location.state !== undefined && location.state.fromRoute !== undefined
  //     ? location.state.fromRoute
  //     : null;

  const authentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  const httpResponseLoaderGlobalState = useSelector(
    (state) => state.adminHttpResponseLoaderGlobalState.value
  );

  if (authentication.isAuthenticated) {
    return <Redirect to="/admin/account" />;
  } else {
    return (
      <>
        <HttpResponseLoader
          isLoading={httpResponseLoaderGlobalState.isLoading}
        />
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
