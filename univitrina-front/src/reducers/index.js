import { combineReducers } from 'redux';
import counterDataReducer from './counterDataReducer';

//  Через комбайн собираем главный редьюсер,
//  значение свойств должно совпадать с полями Store

export default combineReducers({
  counterData: counterDataReducer,
});
