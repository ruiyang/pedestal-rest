import thunk from 'redux-thunk';
import createLogger from 'redux-logger';
import {combineReducers, createStore, applyMiddleware} from 'redux';
import { routerReducer } from 'react-router-redux';
// create a store that has redux-thunk middleware enabled
const loggerMiddleware = createLogger();

const createStoreWithMiddleware = applyMiddleware(
  thunk,
  loggerMiddleware
)(createStore);

let reducers = combineReducers({routing: routerReducer});

const store = createStoreWithMiddleware(reducers);

export default store;
