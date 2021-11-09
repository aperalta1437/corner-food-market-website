import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import { LoginProcessIssueEnum } from "../../login/Utils/loginProcessIssueEnum";

function ProtectedRoute({ component: Component, ...rest }) {
  const authentication = useSelector((state) => state.authentication.value);

  return (
    <Route
      {...rest}
      render={(props) => {
        if (authentication.isAuthenticated) {
          return <Component />;
        } else {
          return (
            <Redirect
              to={{
                pathname: "/login",
                search: `?issue=${LoginProcessIssueEnum.REDIRECTED.name}`,
                state: { fromRoute: props.location },
              }}
            />
          );
        }
      }}
    />
  );
}

export default ProtectedRoute;
