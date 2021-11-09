import React, { useEffect } from "react";
import { Switch, Route } from "react-router-dom";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import authenticationReducer from "./Global/authentication";
import firstFactorAuthenticationReducer from "./Global/firstFactorAuthentication";
import httpResponseLoaderGlobalStateReducer from "./Global/httpResponseLoaderGlobalState";
import HomePage from "./HomePage";
import AccountPage from "./account/AccountPage";
import ProtectedRoute from "./account/Utils/ProtectedRoute";
import { useBeforeunload } from "react-beforeunload";
import { setAuthentication } from "./Global/authentication";

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

  return (
    <Provider store={store}>
      <main
        className={
          window.location.pathname.includes("/login") ? "" : "main-wrap"
        }
      >
        <Switch>
          <Route path="/" component={HomePage} />
          <ProtectedRoute path="/account" component={AccountPage} />
        </Switch>
      </main>
    </Provider>
  );
}

export default App;
