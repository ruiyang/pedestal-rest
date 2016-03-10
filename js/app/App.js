'use strict';

import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router';
function mapStateToProps(state) {
  return state;
}

class App extends React.Component {

  constructor(props) {
    super(props);
  }

  componentWillMount() {
  }

  render () {
    return (
      <div className='container-fluid'>
        {this.props.children}
        <div className='row'>
          <div className='col-xs-4 col-md-4 col-xs-4 col-xs-offset-2 col-md-offset-2 col-xs-offset-2'>
            <Link to='/test' >
              Go To Test Page
            </Link>
          </div>
          <div className='col-xs-4 col-md-4 col-xs-4 col-xs-offset-1 col-md-offset-1 col-xs-offset-1'>
            <Link to='/login' >
              Go To Login
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

export default connect (mapStateToProps)(App);
