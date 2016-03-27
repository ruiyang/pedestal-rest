'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Login from './components/Login';
import Signup from './components/Signup';
import Dashboard from './components/Dashboard';
import { Provider } from 'react-redux';
import { Router, Route, hashHistory } from 'react-router';
import store from './store/todoStore';
import { syncHistoryWithStore } from 'react-router-redux';

const history = syncHistoryWithStore(hashHistory, store);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path="/" component={App}>
        <Route path="/login" component={Login}/>
        <Route path="/signup" component={Signup}/>
        <Route path="/dashboard" component={Dashboard}/>
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app')
);
