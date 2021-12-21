import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import AdminApp from "./admin/AdminApp";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import axios from "axios";

axios.defaults.baseURL = "http://3.133.131.219:8080";

ReactDOM.render(
  <React.StrictMode>
    <Router>
      {/* DO NOT CHANGE ROUTING ORDER BELOW. Otherwise, admin app will be inaccessible. */}
      <Switch>
        <Route path="/admin" component={(props) => <AdminApp />} />
        <Route path="/" component={(props) => <App />} />
      </Switch>
    </Router>
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
