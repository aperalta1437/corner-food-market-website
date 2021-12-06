import React, { useState, useCallback, useEffect } from "react";
import HttpResponseLoader from "../Utils/HttpResponseLoader";
import AdminNewAdminSignupForm from "./AdminNewAdminSignupForm";
import { useParams, useHistory } from "react-router";
import axios from "axios";
import { StatusCodes } from "http-status-codes";

function AdminNewAdminSignupPage() {
  const [isLoading, setIsLoading] = useState(false);
  const { uuid } = useParams();

  const routerHistory = useHistory();

  console.log("The URL param is " + uuid);

  const validateUuid = useCallback(() => {
    axios
      .get(`http://localhost:8080/api/admin/new-admin-signup/${uuid}`, {
        headers: {
          "Access-Control-Allow-Origin": window.location.origin
            ? window.location.origin
            : window.location.protocol +
              "//" +
              window.location.hostname +
              (window.location.port ? ":" + window.location.port : ""), // This approach covers IE.
        },
      })
      .then((response) => {
        if (response.status === StatusCodes.OK) {
          setIsLoading(false);
        }
      })
      .catch((error) => {
        if (error.response.status == StatusCodes.NOT_FOUND) {
          routerHistory.push("/admin/404");
        }
        console.log("Error: " + error);
      });
  }, []);

  //   useEffect(() => {
  //     validateUuid();
  //   }, [validateUuid]);

  return (
    <>
      <HttpResponseLoader isLoading={isLoading} />
      <section className="content-main">{isLoading ? <></> : <AdminNewAdminSignupForm />}</section>
    </>
  );
}

export default AdminNewAdminSignupPage;
