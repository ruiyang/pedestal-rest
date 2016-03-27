'use strict';

import React from 'react';
import { Link } from 'react-router';

export default class Login extends React.Component {
  constructor(props) {
    super(props);
    this.handleLogin = this.handleLogin.bind(this);
  }

  handleLogin() {
    console.log('login');
    const { onLoginClick } = this.props;
    onLoginClick('abc', 'def');
  }

  render () {
    return (
      <div className='componentContainer'>
        <div className='row'>
          <div className='col-xl-2 col-xl-offset-5'>
            <input
              className='form-control'
              placeholder='User Name'>
            </input>
          </div>
        </div>
        <div className='row top-buffer'>
          <div className='col-xl-2 col-xl-offset-5'>
            <input
              className='form-control'
              placeholder='Password'>
            </input>
          </div>
        </div>
        <div className='row top-buffer'>
          <div className='col-xl-12 col-md-12 col-sm-12'>
            <button type='submit' className='btn btn-primary button' onClick={this.handleLogin}>Login</button>
          </div>
        </div>
        <div className='row top-buffer'>
          <div className='col-xl-12 col-md-12 col-sm-12'>
            <Link to='/signup' className='btn btn-default button'>Signup</Link>
          </div>
        </div>
      </div>
    );
  }
}
