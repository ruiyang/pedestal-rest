'use strict';

import { doLogin } from "./actions/loginActions";

import Immutable from 'immutable';
const defaultState = Immutable.List();

export default function login (state = {isLogin: false, loginResult: undefined}, action) {
  switch (action.type) {
    case LOGIN_ACTION:
      return {
        isLogin: true
      };
    case LOGIN_SUCCESS:
      return {
        isLogin: false,
      };
    case SIGNUP_ACTION:
      return state.push(Immutable.fromJS({text: action.text, date: action.date, complete: 'false'}));
    default:
      return state;
  }
}
