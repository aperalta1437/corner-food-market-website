import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";

function ProtectedRoute({ component: Component, ...rest }) {
  const adminAuthentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  return (
    <Route
      {...rest}
      render={(props) => {
        if (adminAuthentication.isAuthenticated) {
          return <Component />;
        } else {
          return (
            <Redirect
              to={{ pathname: "/admin/login", state: { from: props.location } }}
            />
          );
        }
      }}
    />
  );
}

export default ProtectedRoute;
