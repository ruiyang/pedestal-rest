'use strict';

import React from 'react';
import AddItem from './AddItem';
import List from './List';

export default class ListContainer extends React.Component {

  render () {
    return (
      <div className="col-sm-6 col-md-offset-3">
        <div className="col-sm-12">
          <h3 className="text-center"> Todo List</h3>
          <AddItem actions={this.props.actions} />
          <List model={this.props.model} actions={this.props.actions}/>
        </div>
      </div>
    );
  }
}
