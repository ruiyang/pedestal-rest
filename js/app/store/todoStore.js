import {todos, filter} from '../reducers/index';
import thunk from 'redux-thunk';
import {combineReducers, createStore, applyMiddleware} from 'redux';

// create a store that has redux-thunk middleware enabled
const createStoreWithMiddleware = applyMiddleware(
  thunk
)(createStore);

let reducers = combineReducers({todos, filter});

const store = createStoreWithMiddleware(reducers);

export default store;