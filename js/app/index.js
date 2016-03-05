'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { Provider } from 'react-redux';
import store from './store/todoStore';

function run() {
  ReactDOM.render(<Provider store={store}>
    <App />
  </Provider>, document.getElementById('app'));
}

const loadedStates = ['complete', 'loaded', 'interactive'];

if (loadedStates.includes(document.readyState) && document.body) {
  run();
} else {
  window.addEventListener('DOMContentLoaded', run, false);
}

