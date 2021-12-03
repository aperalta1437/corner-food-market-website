import React from "react";
import { css } from "@emotion/react";
import SyncLoader from "react-spinners/ClipLoader";

const loaderStyling = css`
  position: fixed;
  width: 5rem;
  height: 5rem;
  top: 50%;
  left: 50%;
  margin-top: -2.5rem; /* Negative half of height. */
  margin-left: -2.5rem; /* Negative half of width. */
`;

const loaderContainerStyling = {
  position: "fixed",
  top: "0",
  left: "0",
  width: "100%",
  height: "100%",
  background: "white",
  opacity: "0.6",
  zIndex: "100",
};

function HttpResponseLoader({ isLoading }) {
  console.log("HttpResponseLoader: isLoading = " + isLoading);
  return isLoading ? (
    <div
      className="mx-auto text-center spinner-component"
      style={loaderContainerStyling}
    >
      <SyncLoader color="#9ACD32" loading={isLoading} css={loaderStyling} />
    </div>
  ) : null;
}

export default HttpResponseLoader;
