import reqwest from 'reqwest';

export function filterBy(state) {
  return {
    type: 'FILTER_BY',
    state: state
  };
}

export function addTodo(text) {
  return {
    type: 'ADD_TODO',
    text,
    date: new Date()
  };
}

export function deleteTodo(index) {
  return {
    type: 'DELETE_TODO',
    index: index
  };
}

export function getTodos() {
  return (dispatch)=> {
    reqwest({
      method: 'get',
      url: '/api/todos',
      contentType: 'application/json'
    }).then((resp)=> {
      dispatch({
        type: 'GET_TODOS',
        todos: JSON.parse(resp)
      });
    });
  };
}

export function editTodo(index, text) {
  return {
    type: 'EDIT_TODO',
    index: index,
    text,
    date: new Date()
  };
}
