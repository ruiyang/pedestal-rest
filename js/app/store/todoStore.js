import {todos, filter} from '../reducers/index';
import thunk from 'redux-thunk';
import {combineReducers, createStore, applyMiddleware} from 'redux';
import { routerReducer } from 'react-router-redux';
// create a store that has redux-thunk middleware enabled
const createStoreWithMiddleware = applyMiddleware(
  thunk
)(createStore);

let reducers = combineReducers({todos, filter, routing: routerReducer});

const store = createStoreWithMiddleware(reducers);

export default store;
