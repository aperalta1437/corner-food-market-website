import React from "react";
import axios from "axios";

function AdminAccountItem({ item, onRemoveItem }) {
  return (
    <div className="col">
      <div className="card card-product-grid">
        <a href="#" className="img-wrap">
          {" "}
          <img
            src={`${axios.defaults.baseURL}/` + item.mainImage.sourceRelativePathName}
            alt="Product"
          />{" "}
        </a>
        <div className="info-wrap">
          <a href="#" className="title text-truncate">
            {item.name}
          </a>
          <div className="price mb-2">${item.price}</div>

          <a href="#" data-bs-toggle="dropdown" className="btn btn-sm btn-light">
            <i className="material-icons md-edit"></i> Edit
          </a>
          <a className="btn btn-sm btn-outline-danger" onClick={() => onRemoveItem(item.id)}>
            <i className="material-icons md-delete_forever"></i> Remove
          </a>
        </div>
      </div>
    </div>
  );
}

export default AdminAccountItem;
