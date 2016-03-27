'use strict';

import React from 'react';

export default class Signup extends React.Component {
  render () {
    return (
      <div className="componentContainer">
        <div className="row">
          <div className="col-xl-2 col-xl-offset-5">
            <input
              className="form-control"
              placeholder="User Name">
            </input>
          </div>
        </div>
        <div className="row top-buffer">
          <div className="col-xl-2 col-xl-offset-5">
            <input
              className="form-control"
              placeholder="Password">
            </input>
          </div>
        </div>
        <div className="row top-buffer">
          <div className="col-xl-2 col-xl-offset-5">
            <input
              className="form-control"
              placeholder="Confirm Password">
            </input>
          </div>
        </div>
        <div className="row top-buffer">
          <div className="col-xl-12 col-md-12 col-sm-12">
            <button type="submit" className="btn btn-primary button">Signup</button>
          </div>
        </div>
      </div>
    );
  }
}
