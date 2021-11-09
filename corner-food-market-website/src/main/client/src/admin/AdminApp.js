import React, { useEffect } from "react";
import AdminAccountAsideMenu from "./account/AdminAccountAsideMenu";
import { Switch, Route, Redirect } from "react-router-dom";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import adminAuthenticationReducer from "./Global/adminAuthentication";
import adminFirstFactorAuthenticationReducer from "./Global/adminFirstFactorAuthentication";
import adminHttpResponseLoaderGlobalStateReducer from "./Global/adminHttpResponseLoaderGlobalState";
import AdminLoginPage from "./login/AdminLoginPage";
import AdminAccountPage from "./account/AdminAccountPage";
import AdminProtectedRoute from "./account/Utils/AdminProtectedRoute";
import { useBeforeunload } from "react-beforeunload";
import { setAuthentication } from "./Global/adminAuthentication";

const adminStore = configureStore({
  reducer: {
    adminAuthentication: adminAuthenticationReducer,
    adminFirstFactorAuthentication: adminFirstFactorAuthenticationReducer,
    adminHttpResponseLoaderGlobalState:
      adminHttpResponseLoaderGlobalStateReducer,
  },
  devTools: process.env.NODE_ENV !== "production", // Disable Redux toolkit Devtools in production application.
});

function AdminApp() {
  useBeforeunload((event) => {
    const adminStoreState = (({ adminAuthentication }) => ({
      adminAuthentication,
    }))(adminStore.getState());
    window.sessionStorage.setItem(
      "adminStoreState",
      JSON.stringify(adminStoreState)
    );
  });

  useEffect(() => {
    const adminStoreState = JSON.parse(
      window.sessionStorage.getItem("adminStoreState")
    );
    window.sessionStorage.removeItem("adminStoreState");
    if (adminStoreState) {
      adminStore.dispatch(
        setAuthentication(adminStoreState.adminAuthentication.value)
      );
    }
  }, []);

  if (window.location.pathname === "/admin") {
    return <Redirect to="/admin/login" />;
  } else {
    return (
      <Provider store={adminStore}>
        <Switch>
          <AdminProtectedRoute
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
            <AdminProtectedRoute
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
