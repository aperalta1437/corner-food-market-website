import React from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import { AdminLoginProcessIssueEnum } from "../../login/Utils/adminLoginProcessIssueEnum";

function AdminProtectedRoute({ component: Component, ...rest }) {
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
              to={{
                pathname: "/admin/login",
                search: `?issue=${AdminLoginProcessIssueEnum.REDIRECTED.name}`,
                state: { fromRoute: props.location },
              }}
            />
          );
        }
      }}
    />
  );
}

export default AdminProtectedRoute;
