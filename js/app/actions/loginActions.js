import reqwest from 'reqwest';
import { push } from 'react-router-redux';

export const LOGIN_ACTION = 'LOGIN';
export const LOGIN_IN_PROCESS = 'LOGIN_IN_PROCESS';
export const LOGIN_FAILURE = 'LOGIN_FAILURE';
export const SIGNUP_ACTION = 'SIGNUP';
export const LOGIN_SUCCESS = 'LOGIN_SUCCESS';

export function login() {
  return {
    type: LOGIN_ACTION
  };
}

export function loginInProcess() {
  return {
    type: LOGIN_IN_PROCESS
  };
}

export function loginFailure(msg) {
  return {
    type: LOGIN_FAILURE,
    msg
  };
}

export function signup() {
  return {
    type: SIGNUP_ACTION,
    username,
    password
  };
}

export function doLogin(username, password) {

  return function (dispatch) {

    dispatch(loginInProcess());

    // The function called by the thunk middleware can return a value,
    // that is passed on as the return value of the dispatch method.

    // In this case, we return a promise to wait for.
    // This is not required by thunk middleware, but it is convenient for us.

    return reqwest({
      url: '/login',
      method: 'post',
      data: { username: username, password: password }
    }).then(() => dispatch(push('/dashboard')), (err, msg) => dispatch(loginFailure(msg)));
  };
}
