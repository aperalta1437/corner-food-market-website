import React from "react";

function AdminAccountItemsList() {
  return (
    <section className="content-main">
      <div className="content-header">
        <h2 classNameName="content-title">Products grid</h2>
        <div>
          <a href="#" className="btn btn-light">
            Export
          </a>
          <a href="#" className="btn btn-light">
            Import
          </a>
          <a href="#" className="btn btn-primary">
            Create new
          </a>
        </div>
      </div>

      <div className="card mb-4">
        <header className="card-header">
          <div className="row gx-3">
            <div className="col-lg-4 col-md-6 me-auto">
              <input
                type="text"
                placeholder="Search..."
                className="form-control"
              />
            </div>
            <div className="col-lg-2 col-6 col-md-3">
              <select className="form-select">
                <option>All category</option>
                <option>Electronics</option>
                <option>Clothings</option>
                <option>Something else</option>
              </select>
            </div>
            <div className="col-lg-2 col-6 col-md-3">
              <select className="form-select">
                <option>Latest added</option>
                <option>Cheap first</option>
                <option>Most viewed</option>
              </select>
            </div>
          </div>
        </header>
        <div className="card-body">
          <div className="row gx-3 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-xl-4 row-cols-xxl-5">
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/1.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Just another product name
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/2.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Brown Winter Jacket for Men
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/3.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Jeans short new model
                  </a>
                  <div className="price mb-2">$179.00</div>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/4.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Travel Bag XL
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/5.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Just another product name
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/6.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Just another product name
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/7.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Just another product name
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
            <div className="col">
              <div className="card card-product-grid">
                <a href="#" className="img-wrap">
                  {" "}
                  <img src="images/items/8.jpg" alt="Product" />{" "}
                </a>
                <div className="info-wrap">
                  <a href="#" className="title text-truncate">
                    Apple Airpods CB-133
                  </a>
                  <div className="price mb-2">$179.00</div>

                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-light"
                  >
                    <i className="material-icons md-edit"></i> Edit
                  </a>
                  <a
                    href="#"
                    data-bs-toggle="dropdown"
                    className="btn btn-sm btn-outline-danger"
                  >
                    <i className="material-icons md-delete_forever"></i> Delete
                  </a>
                </div>
              </div>
            </div>
          </div>

          <nav className="float-end mt-4" aria-label="Page navigation">
            <ul className="pagination">
              <li className="page-item disabled">
                <a className="page-link" href="#">
                  Previous
                </a>
              </li>
              <li className="page-item active">
                <a className="page-link" href="#">
                  1
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  2
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  3
                </a>
              </li>
              <li className="page-item">
                <a className="page-link" href="#">
                  Next
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </section>
  );
}

export default AdminAccountItemsList;
