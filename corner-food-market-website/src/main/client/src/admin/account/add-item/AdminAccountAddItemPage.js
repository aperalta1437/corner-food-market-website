import React, { useState } from "react";
import axios from "axios";
import { useSelector } from "react-redux";
import { StatusCodes } from "http-status-codes";

function AdminAccountAddItemPage() {
  const [itemTitle, setItemTitle] = useState("");
  const [itemDescription, setItemDescription] = useState("");
  const [itemPrice, setItemPrice] = useState("");
  const [itemCategory, setItemCategory] = useState("");
  const [itemImageFile, setItemImageFile] = useState(null);

  const adminAuthentication = useSelector(
    (state) => state.adminAuthentication.value
  );

  const handleSelectedFile = (event) => {
    console.log(event.target.files[0]);
    setItemImageFile(event.target.files[0]);
  };

  const uploadNewItem = (event) => {
    const formData = new FormData();
    formData.append("itemTitle", itemTitle);
    formData.append("itemDescription", itemDescription);
    formData.append("itemPrice", itemPrice);
    formData.append("itemCategory", itemCategory);
    formData.append("itemImageFile", itemImageFile);
    axios
      .post("", formData, {
        headers: {
          Authorization: adminAuthentication.accessToken,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "http://localhost:3000",
        },
      })
      .then((response) => {
        console.log(response);
      })
      .catch({});
  };

  return (
    <section className="content-main" style={{ maxWidth: "920px" }}>
      <div className="content-header">
        <h2 className="content-title">Add Item</h2>
      </div>

      <div className="card mb-4">
        <div className="card-body">
          <div className="row">
            <div className="col-md-4">
              <h6>1. General info</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4">
                <label className="form-label">Item title</label>
                <input
                  type="text"
                  placeholder="Type here"
                  className="form-control"
                  onChange={(event) => {
                    setItemTitle(event.target.value);
                  }}
                  required
                />
              </div>
              <div className="mb-4">
                <label className="form-label">Description</label>
                <textarea
                  placeholder="Type here"
                  className="form-control"
                  rows="4"
                  onChange={(event) => {
                    setItemDescription(event.target.value);
                  }}
                  required
                ></textarea>
              </div>
            </div>
          </div>

          <hr className="mb-4 mt-0" />

          <div className="row">
            <div className="col-md-4">
              <h6>2. Pricing</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4" style={{ maxWidth: "250px" }}>
                <label className="form-label">Price in USD</label>
                <input
                  type="text"
                  placeholder="00.00"
                  className="form-control"
                  onChange={(event) => {
                    setItemPrice(event.target.value);
                  }}
                  required
                />
              </div>
            </div>
          </div>

          <hr className="mb-4 mt-0" />

          <div className="row">
            <div className="col-md-4">
              <h6>3. Category</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4">
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    checked=""
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Clothes </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Electronics </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Sports </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Automobiles </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Home interior </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Smartphones </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Books </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Kids item </span>
                </label>
                <label
                  className="mb-2 form-check form-check-inline"
                  style={{ width: "45%" }}
                >
                  <input
                    className="form-check-input"
                    name="mycategory"
                    type="radio"
                  />
                  <span className="form-check-label"> Others </span>
                </label>
              </div>
            </div>
          </div>

          <hr className="mb-4 mt-0" />

          <div className="row">
            <div className="col-md-4">
              <h6>4. Media</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4">
                <label className="form-label">Images</label>
                <input
                  className="form-control"
                  type="file"
                  onChange={handleSelectedFile}
                />
              </div>
            </div>
          </div>
          <hr className="mb-4 mt-0" />

          <div className="d-flex justify-content-end gap-2">
            <button type="button" className="btn btn-light">
              Save as draft
            </button>
            <button
              type="button"
              className="btn btn-primary"
              onClick={uploadNewItem}
            >
              Add to Website{" "}
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}

export default AdminAccountAddItemPage;
