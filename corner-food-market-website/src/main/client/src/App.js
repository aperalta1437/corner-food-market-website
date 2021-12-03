import React, { useEffect } from "react";
import { Switch, Route } from "react-router-dom";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import authenticationReducer from "./Global/authentication";
import firstFactorAuthenticationReducer from "./Global/firstFactorAuthentication";
import httpResponseLoaderGlobalStateReducer from "./Global/httpResponseLoaderGlobalState";
import PublicPages from "./PublicPages";
import AccountPages from "./account/AccountPages";
import ProtectedRoute from "./account/Utils/ProtectedRoute";
import { useBeforeunload } from "react-beforeunload";
import { setAuthentication } from "./Global/authentication";
import Header from "./Header";
import NavigationBar from "./NavigationBar";
import Footer from "./Footer";
import AccountHeader from "./account/AccountHeader";

const store = configureStore({
  reducer: {
    authentication: authenticationReducer,
    firstFactorAuthentication: firstFactorAuthenticationReducer,
    httpResponseLoaderGlobalState: httpResponseLoaderGlobalStateReducer,
  },
  devTools: process.env.NODE_ENV !== "production", // Disable Redux toolkit Devtools in production application.
});

function App() {
  useBeforeunload((event) => {
    const storeState = (({ authentication }) => ({
      authentication,
    }))(store.getState());
    window.sessionStorage.setItem("storeState", JSON.stringify(storeState));
  });

  useEffect(() => {
    const storeState = JSON.parse(window.sessionStorage.getItem("storeState"));
    window.sessionStorage.removeItem("storeState");
    if (storeState) {
      store.dispatch(setAuthentication(storeState.authentication.value));
    }
  }, []);

  // require("./Static/Customer/css/bootstrap.css");
  return (
    <Provider store={store}>
      <Switch>
        <Route path="/" component={Header} />
        <ProtectedRoute path="/account" component={AccountHeader} />
      </Switch>
      <NavigationBar />
      <main
        className={
          window.location.pathname.includes("/login") ? "" : "main-wrap"
        }
      >
        <Switch>
          <Route path="/" component={PublicPages} />
          <ProtectedRoute path="/account" component={AccountPages} />
        </Switch>
      </main>
      <Footer />
    </Provider>
  );
}

export default App;
