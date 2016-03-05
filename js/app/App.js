'use strict';

import React from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import ListContainer from './ListContainer';
import * as TodoActions from './actions/todoActions';


function mapStateToProps(state) {
  return state;
}

class App extends React.Component {

  constructor(props) {
    super(props);
    this.actions = bindActionCreators(TodoActions, props.dispatch);
  }

  componentWillMount() {
    this.actions.getTodos();
  }

  render () {
    return (
      <div className="container">
        <div className="row">
          <ListContainer model={this.props.todos} actions={this.actions} />
        </div>
      </div>
    );
  }

}

export default connect (mapStateToProps)(App);
