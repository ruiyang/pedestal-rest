const defaultState = 'SHOW_ALL'; //'ALL_Complete'

export default function filter (state = defaultState, action) {
  switch (action.type) {
  case 'SET_FILTER':
    return action.filter;
  default:
    return state;
  }
}