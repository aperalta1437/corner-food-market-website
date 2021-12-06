import React, { useEffect } from "react";
import AdminAccountAsideMenu from "./account/AdminAccountAsideMenu";
import { Switch, Route, Redirect } from "react-router-dom";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import adminAuthenticationReducer from "./Global/adminAuthentication";
import adminFirstFactorAuthenticationReducer from "./Global/adminFirstFactorAuthentication";
import adminHttpResponseLoaderGlobalStateReducer from "./Global/adminHttpResponseLoaderGlobalState";
import AdminLoginPage from "./login/AdminLoginPage";
import AdminNewAdminSignupPage from "./new-admin-signup/AdminNewAdminSignupPage";
import AdminAccountPages from "./account/AdminAccountPages";
import AdminProtectedRoute from "./account/Utils/AdminProtectedRoute";
import AdminHeader from "./login/AdminHeader";
import { useBeforeunload } from "react-beforeunload";
import { setAuthentication } from "./Global/adminAuthentication";

const adminStore = configureStore({
  reducer: {
    adminAuthentication: adminAuthenticationReducer,
    adminFirstFactorAuthentication: adminFirstFactorAuthenticationReducer,
    adminHttpResponseLoaderGlobalState: adminHttpResponseLoaderGlobalStateReducer,
  },
  devTools: process.env.NODE_ENV !== "production", // Disable Redux toolkit Devtools in production application.
});

function AdminApp() {
  useBeforeunload((event) => {
    const adminStoreStateToPersist = (({ adminAuthentication }) => ({
      adminAuthentication,
    }))(adminStore.getState());
    window.sessionStorage.setItem(
      "adminStorePersistedState",
      JSON.stringify(adminStoreStateToPersist)
    );
  });

  useEffect(() => {
    const adminStorePersistedState = JSON.parse(
      window.sessionStorage.getItem("adminStorePersistedState")
    );
    window.sessionStorage.removeItem("adminStorePersistedState");
    if (adminStorePersistedState) {
      adminStore.dispatch(setAuthentication(adminStorePersistedState.adminAuthentication.value));
    }
  }, []);

  console.log("domain:  " + window.location.origin);

  // require("../Static/Administrator/css/bootstrap.css");
  if (window.location.pathname === "/admin") {
    return <Redirect to="/admin/login" />;
  } else {
    return (
      <Provider store={adminStore}>
        <Switch>
          <AdminProtectedRoute path="/admin/account" component={AdminAccountAsideMenu} />
        </Switch>
        <main className={!window.location.pathname.includes("/admin/account") ? "" : "main-wrap"}>
          {!window.location.pathname.includes("/admin/account") ? <AdminHeader /> : <></>}
          <Switch>
            <Route path="/admin/login" component={AdminLoginPage} />{" "}
            {/* Do NOT use "exact" so we can navigate to .../2fa-code */}
            <Route path="/admin/new-admin-signup/:uuid" exact component={AdminNewAdminSignupPage} />
            <AdminProtectedRoute path="/admin/account" component={AdminAccountPages} />
          </Switch>
        </main>
      </Provider>
    );
  }
}

export default AdminApp;
