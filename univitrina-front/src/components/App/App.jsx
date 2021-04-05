import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import logo from './App_logo.svg';
import './App.css';
import Router from '../../router/index';
import { incrementCounter } from '../../ actions';

// Пока для примера прикрутил роутер между header и footer
// но потом в зависимости от дизайна переделаем

function App() {
  // Пример: состояние счетчика хранит Redux
  const counter = useSelector((state) => state.counterData);
  const dispatch = useDispatch();
  const hundleInc = (e) => {
    e.preventDefault();
    dispatch(incrementCounter());
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <button type="button" onClick={hundleInc}>
          Counter=
          {counter}
        </button>
      </header>
      <Router />
      <footer>Footer</footer>
    </div>
  );
}

export default App;
