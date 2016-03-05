'use strict';

import React from 'react';

export default class List extends React.Component {

  _remove (index) {
    this.props.actions.deleteTodo(index);
  }

  render () {
    let listItems = this.props.model.map(function(item, index) {
      return (
        <li key={index} className="list-group-item">
          <span
            className="glyphicon glyphicon-remove"
            onClick={this._remove.bind(this, index)}>
          </span>
          <span className="index">- {index} - </span>
          <span className="content">{ item.get('text') } {'   complete: '} { item.get('complete') }</span>
        </li>
      );
    }.bind(this));

    return (
      <ul className="items-wrapper">
        {listItems}
      </ul>
    );
  }

}
