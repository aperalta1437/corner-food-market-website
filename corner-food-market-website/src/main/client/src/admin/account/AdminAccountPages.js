import React from "react";
import AdminAccountHeader from "./AdminAccountHeader";
import AdminAccountItemsListPage from "./AdminAccountItemsListPage";
import AdminAccountAddItemPage from "./add-item/AdminAccountAddItemPage";
import AdminAccountCreateNewAdminPage from "./create-new-admin/AdminAccountCreateNewAdminPage";
import { Switch, Route } from "react-router-dom";
import { useSelector } from "react-redux";
import HttpResponseLoader from "../Utils/HttpResponseLoader";

function AdminAccountPages() {
  const httpResponseLoaderGlobalState = useSelector(
    (state) => state.adminHttpResponseLoaderGlobalState.value
  );

  return (
    <>
      <HttpResponseLoader isLoading={httpResponseLoaderGlobalState.isLoading} />
      <AdminAccountHeader />
      <Switch>
        <Route path="/admin/account/" exact component={AdminAccountItemsListPage} />
        <Route path="/admin/account/add-item" exact component={AdminAccountAddItemPage} />
        <Route
          path="/admin/account/create-new-admin"
          exact
          component={AdminAccountCreateNewAdminPage}
        />
      </Switch>
    </>
  );
}

export default AdminAccountPages;
