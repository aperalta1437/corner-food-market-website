import React from "react";
import AdminAccountHeader from "./AdminAccountHeader";
import AdminAccountItemsList from "./AdminAccountItemsList";
import { Switch, Route } from "react-router-dom";

function AdminAccountPage() {
  return (
    <>
      <AdminAccountHeader />
      <Switch>
        <Route path="/admin/account/" exact component={AdminAccountItemsList} />
      </Switch>
    </>
  );
}

export default AdminAccountPage;
