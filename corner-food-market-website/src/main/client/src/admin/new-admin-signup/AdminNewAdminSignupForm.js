import React from "react";

function AdminNewAdminSignupForm() {
  return (
    <div className="card shadow mx-auto" style={{ maxWidth: "520px", marginTop: "60px" }}>
      <div className="card-body">
        <h4 className="card-title mb-4">New Admin Signup</h4>
        <form>
          <div className="row mb-3">
            <div className="col form-group">
              <label>First name</label>
              <input
                type="text"
                className="form-control"
                placeholder=""
                required
                minlength="2"
                maxlength="25"
              />
            </div>
            <div className="col form-group">
              <label>Last name</label>
              <input
                type="text"
                className="form-control"
                placeholder=""
                required
                minlength="2"
                maxlength="35"
              />
            </div>
          </div>
          <div className="row mb-3">
            <div className="col form-group">
              <label>
                <br />
                Middle name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder=""
                minlength="2"
                maxlength="25"
              />
            </div>
            <div className="col form-group">
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
          </div>
          <div class="row mb-5">
            <div class="form-group col-md-6">
              <label>Create password</label>
              <input
                class="form-control"
                id="input-password"
                type="password"
                required
                minlength="8"
                maxlength="64"
              />
            </div>
            <div class="form-group col-md-6">
              <label>Repeat password</label>
              <input
                class="form-control"
                id="input-password-confirm"
                type="password"
                required
                minlength="8"
                maxlength="64"
              />
            </div>
          </div>
          <div className="mb-4">
            <button type="submit" className="btn btn-primary w-100">
              {" "}
              Sign Up{" "}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AdminNewAdminSignupForm;
