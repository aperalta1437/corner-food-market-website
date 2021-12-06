import React from "react";

function AdminAccountCreateNewAdminPage() {
  return (
    <section className="content-main">
      <div className="card shadow mx-auto" style={{ maxWidth: "520px", marginTop: "60px" }}>
        <div className="card-body">
          <h4 className="card-title mb-4">New Admin Account</h4>
          <form>
            <div className="mb-3">
              <label className="form-label">Email</label>
              <input
                className="form-control"
                type="email"
                placeholder="Type email"
                type="text"
                required
              />
            </div>
            <div className="form-group mb-3">
              <label>
                Cell Phone Number
                <br />
                <small className="form-text text-muted">(###-###-####)</small>
              </label>
              <input
                type="tel"
                pattern="[0-9]{3}[0-9]{3}[0-9]{4}"
                className="form-control"
                placeholder=""
                required
              />
            </div>
            <div className="mb-4">
              <button type="submit" className="btn btn-primary w-100">
                {" "}
                Notify{" "}
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
}

export default AdminAccountCreateNewAdminPage;
