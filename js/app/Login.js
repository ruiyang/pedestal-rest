'use strict';

import React from 'react';

export default class LoginPage extends React.Component {
  render () {
    return (
      <div className="loginContainer">
      <div className="row">
        <div className="col-xl-2 col-xl-offset-5">
          <input className="form-control" placeholder="User Name">
          </input>
        </div>
      </div>
      <div className="row top-buffer">
        <div className="col-xl-2 col-xl-offset-5">
          <input className="form-control" placeholder="Password">
        </input>
        </div>
        </div>
      </div>
    );
  }
}
