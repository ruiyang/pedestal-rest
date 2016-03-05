import Immutable from 'immutable';
const defaultState = Immutable.List();

export default function todos (state = defaultState, action) {
  switch (action.type) {
  // case 'FILTER_BY':
  //   return Immutable.fromJS(
  //     action.todos
  //   );
  case 'GET_TODOS':
    return state = Immutable.fromJS(
      action.todos
    );
  case 'ADD_TODO':
    return state.push(Immutable.fromJS({text: action.text, date: action.date, complete: 'false'}));
  case 'EDIT_TODO':
    return state.update(action.id, (o) => {
      o.text = action.text;
      return o;
    });
  case 'DELETE_TODO':
    return state.delete(action.index);
  default:
    return state;
  }
}