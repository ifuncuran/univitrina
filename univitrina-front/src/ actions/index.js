import { createAction } from 'redux-actions';

/* Библиотека redux-actions позволяет убрать дублирование кода 
и ИМХО делает код красивее и компактнее
 Функция createAction принимает тип действия (свойство type)
 и возвращает функцию, принимающую payload */

// пример создания экшенов
export const incrementCounter = createAction('INCREMENT_COUNTER');
export const decrementCounter = createAction('DECREMENT_COUNTER');
