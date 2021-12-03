import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";
import { resetAuthentication } from "../../Global/adminAuthentication";
import { AdminLoginProcessIssueEnum } from "../../login/Utils/adminLoginProcessIssueEnum";
import { useSelector, useDispatch } from "react-redux";
import { StatusCodes } from "http-status-codes";
import { flipIsLoading } from "../../Global/adminHttpResponseLoaderGlobalState";

function AdminAccountAddItemPage() {
  const dispatch = useDispatch();
  const routerHistory = useHistory();

  const [itemTitle, setItemTitle] = useState("");
  const [itemDescription, setItemDescription] = useState("");
  const [itemPrice, setItemPrice] = useState(null);
  const [itemCategoryId, setItemCategoryId] = useState(null);
  const [itemSku, setItemSku] = useState("");
  const [itemQuantity, setItemQuantity] = useState(null);
  const [itemImageFile, setItemImageFile] = useState(null);

  const adminAuthentication = useSelector((state) => state.adminAuthentication.value);

  let [itemCategoriesList, setItemCategoriesList] = useState([]);

  const fetchItemCategoriesList = useCallback(() => {
    axios
      .get("http://localhost:8080/api/admin/account/add-item/item-categories", {
        headers: {
          Authorization: adminAuthentication.accessToken,
        },
      })
      .then((response) => {
        console.log(adminAuthentication.accessToken);
        setItemCategoriesList(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        if (error.response.status === StatusCodes.UNAUTHORIZED) {
          dispatch(resetAuthentication());
          routerHistory.push(
            "/admin/login?issue=" + AdminLoginProcessIssueEnum.EXPIRED_SESSION.name
          );
        }
        console.log("Error: " + error);
      });
  }, []);

  useEffect(() => {
    fetchItemCategoriesList();
  }, [fetchItemCategoriesList]);

  const handleSelectedFile = (event) => {
    console.log(event.target.files[0]);
    setItemImageFile(event.target.files[0]);
  };

  const submitNewItemForm = (event) => {
    event.preventDefault();

    dispatch(flipIsLoading());

    const formData = new FormData();
    formData.append("itemTitle", itemTitle);
    formData.append("itemDescription", itemDescription);
    formData.append("itemPrice", itemPrice);
    formData.append("itemCategoryId", itemCategoryId);
    formData.append("itemSku", itemSku);
    formData.append("itemQuantity", itemQuantity);
    formData.append("itemImageFile", itemImageFile);
    axios
      .post("http://localhost:8080/api/admin/account/add-item/upload-new-item", formData, {
        headers: {
          Authorization: adminAuthentication.accessToken,
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": window.location.origin
            ? window.location.origin
            : window.location.protocol +
              "//" +
              window.location.hostname +
              (window.location.port ? ":" + window.location.port : ""), // This approach covers IE.
        },
      })
      .then((response) => {
        console.log(response);
      })
      .catch({});

    dispatch(flipIsLoading());
  };

  return (
    <section className="content-main" style={{ maxWidth: "920px" }}>
      <div className="content-header">
        <h2 className="content-title">Add Item</h2>
      </div>

      <div className="card mb-4">
        <form className="card-body" onSubmit={submitNewItemForm}>
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
                  type="number"
                  min="0.01"
                  max="999.99"
                  step="0.01"
                  placeholder="#.##"
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
              <h6>3. Association</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4">
                <label className="form-label">Category</label>
                <select
                  class="form-select"
                  onChange={(event) => {
                    setItemCategoryId(event.target.value);
                    console.log(itemCategoryId);
                  }}
                  required
                >
                  <option value="" selected disabled hidden>
                    Choose here
                  </option>
                  {itemCategoriesList.map((category) => (
                    <option key={category.id} value={category.id}>
                      {" "}
                      {category.name}{" "}
                    </option>
                  ))}
                </select>
              </div>
              <div className="mb-4">
                <label className="form-label">SKU</label>
                <input
                  type="text"
                  placeholder="Type here"
                  className="form-control"
                  onChange={(event) => {
                    setItemSku(event.target.value);
                  }}
                  required
                />
              </div>
            </div>
          </div>

          <hr className="mb-4 mt-0" />

          <div className="row">
            <div className="col-md-4">
              <h6>4. Inventory</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4" style={{ maxWidth: "250px" }}>
                <label className="form-label">Quantity</label>
                <input
                  type="number"
                  placeholder=""
                  className="form-control"
                  onChange={(event) => {
                    setItemQuantity(event.target.value);
                  }}
                  required
                />
              </div>
            </div>
          </div>

          <hr className="mb-4 mt-0" />

          <div className="row">
            <div className="col-md-4">
              <h6>5. Media</h6>
            </div>
            <div className="col-md-8">
              <div className="mb-4">
                <label className="form-label">Images</label>
                <input
                  className="form-control"
                  type="file"
                  onChange={handleSelectedFile}
                  required
                />
              </div>
            </div>
          </div>
          <hr className="mb-4 mt-0" />

          <div className="d-flex justify-content-end gap-2">
            <button type="button" className="btn btn-light">
              Save as draft
            </button>
            <button type="submit" className="btn btn-primary">
              Add to Website{" "}
            </button>
          </div>
        </form>
      </div>
    </section>
  );
}

export default AdminAccountAddItemPage;
