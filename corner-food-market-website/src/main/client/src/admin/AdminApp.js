import React from "react";
import AdminAccountAsideMenu from "./account/AdminAccountAsideMenu";
import { Switch, Route, Redirect } from "react-router-dom";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import adminAuthenticationReducer from "./Global/adminAuthentication";
import adminFirstFactorAuthenticationReducer from "./Global/adminFirstFactorAuthentication";
import adminHttpResponseLoaderGlobalStateReducer from "./Global/adminHttpResponseLoaderGlobalState";
import AdminLoginPage from "./login/AdminLoginPage";
import AdminAccountPage from "./account/AdminAccountPage";
import ProtectedRoute from "./account/Utils/ProtectedRoute";

const adminStore = configureStore({
  reducer: {
    adminAuthentication: adminAuthenticationReducer,
    adminFirstFactorAuthentication: adminFirstFactorAuthenticationReducer,
    adminHttpResponseLoaderGlobalState:
      adminHttpResponseLoaderGlobalStateReducer,
  },
  // devTools: false, // Disable Redux toolkit Devtools
});

function AdminApp() {
  if (window.location.pathname === "/admin") {
    return <Redirect to="/admin/login" />;
  } else {
    return (
      <Provider store={adminStore}>
        <Switch>
          <ProtectedRoute
            path="/admin/account"
            component={AdminAccountAsideMenu}
          />
        </Switch>
        <main
          className={
            window.location.pathname.includes("/admin/login") ? "" : "main-wrap"
          }
        >
          <Switch>
            <Route path="/admin/login" component={AdminLoginPage} />
            <ProtectedRoute
              path="/admin/account"
              component={AdminAccountPage}
            />
          </Switch>
        </main>
      </Provider>
    );
  }
}

export default AdminApp;
