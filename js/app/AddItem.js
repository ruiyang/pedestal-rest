'use strict';

import React from 'react';

export default class AddItem extends React.Component {

  constructor () {
    super();
    this.state = {
      model: ''
    };
  }

  _handleChange (e) {
    this.setState({
      model: e.target.value
    });
  }

  _handleSubmit (e) {
    if (e.keyCode === 13) {
      if (!this.state.model) {
        return;
      }
      this.props.actions.addTodo(this.state.model);
      this.setState({
        model: ''
      });
    }
  }

  render () {
    return (
      <div>
        <input
          tupe="text"
          className="form-control"
          value={this.state.model}
          placeholder="New Item"
          onKeyDown={this._handleSubmit.bind(this)}
          onChange={this._handleChange.bind(this)}
          />
      </div>
    );
  }
}
