'use strict';

import React from 'react';
import { connect } from 'react-redux';
import { doLogin } from './actions/loginActions';

function mapStateToProps(state) {
  return state;
}

function mapDispatchToProps(dispatch) {
  return {
    onLoginClick: (username, password) => {
      dispatch(doLogin(username, password));
    }
  };
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
        {React.cloneElement(this.props.children, Object.assign({}, this.props))}
      </div>
    );
  }
}

export default connect (mapStateToProps, mapDispatchToProps)(App);
