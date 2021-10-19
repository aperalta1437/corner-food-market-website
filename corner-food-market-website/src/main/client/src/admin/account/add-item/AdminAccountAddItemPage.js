import React from 'react'

function AdminAccountAddItemPage() {
    return (
        <section className="content-main" style={{maxWidth: "920px"}}>

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
                                <input type="text" placeholder="Type here" className="form-control" />
                            </div>
                            <div className="mb-4">
                                <label className="form-label">Description</label>
                                <textarea placeholder="Type here" className="form-control" rows="4"></textarea>
                            </div>
                        </div>
                    </div> 
                    
                    <hr className="mb-4 mt-0" />

                    <div className="row"> 
                        <div className="col-md-4">
                            <h6>2. Pricing</h6>
                        </div>
                        <div className="col-md-8">
                            <div className="mb-4" style={{maxWidth: "250px"}}>
                                <label className="form-label">Price in USD</label>
                                <input type="text" placeholder="00.0" className="form-control" />
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
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" checked="" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Clothes </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Electronics </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Sports </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Automobiles </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Home interior </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Smartphones </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Books </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Kids item </span>
                                </label>
                                <label className="mb-2 form-check form-check-inline" style={{width: "45%"}}>
                                    <input className="form-check-input" name="mycategory" type="radio" />
                                    <span className="form-check-label">  Others </span>
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
                                <input className="form-control" type="file" />
                            </div>
                        </div>
                    </div> 
                    <hr className="mb-4 mt-0" />

                    <div className="d-flex justify-content-end gap-2">
                        <button type="button" className="btn btn-light">Save as draft</button>
                        <button type="button" className="btn btn-primary">Add to Website </button>
                    </div>
                </div>
            </div>


        </section>
    )
}

export default AdminAccountAddItemPage
