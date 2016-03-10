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
      <div className='container'>
        <div className='row'>
          {this.props.children}
          <Link to='/test' >
            Go To Test Page
          </Link>
          <Link to='/login' >
            Go To Login
          </Link>
        </div>
      </div>
    );
  }

}

export default connect (mapStateToProps)(App);
