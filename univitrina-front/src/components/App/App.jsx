import React from 'react';
import logo from './App_logo.svg';
import './App.css';
import Router from '../../router/index';

// Пока для примера прикрутил роутер между header и footer
// но потом в зависимости от дизайна переделаем

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      </header>
      <Router />
      <footer>Footer</footer>
    </div>
  );
}

export default App;
