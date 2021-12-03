import React from "react";
import { Switch, Route } from "react-router-dom";
import ItemsListPage from "./ItemsListPage";
import LoginPage from "./login/LoginPage";
import ReviewsPage from "./reviews/ReviewsPage";
import SignupPage from "./signup/SignupPage";

function HomePage() {
  return (
    <>
      <Switch>
        <Route path="/" exact component={ItemsListPage} />
        <Route path="/signup" exact component={SignupPage} />
        <Route path="/login" exact component={LoginPage} />
        <Route path="/reviews" exact component={ReviewsPage} />
      </Switch>
    </>
  );
}

export default HomePage;
