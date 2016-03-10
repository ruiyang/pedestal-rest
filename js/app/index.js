'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import LoginPage from './Login';
import TestPage from './TestPage';
import { Provider } from 'react-redux';
import { Router, Route, hashHistory } from 'react-router';
import store from './store/todoStore';
import { syncHistoryWithStore } from 'react-router-redux';

const history = syncHistoryWithStore(hashHistory, store);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path="/" component={App}>
        <Route path="/login" component={LoginPage}/>
        <Route path="/test" component={TestPage}/>
      </Route>
    </Router>
  </Provider>,
  document.getElementById('app')
);
