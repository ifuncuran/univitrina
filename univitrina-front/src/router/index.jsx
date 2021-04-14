import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { incrementCounter } from '../actions';
import Professions from '../components/Professions';
import Specializations from '../components/Specializations';
import Universities from '../components/Universities';
import Main from '../components/Main';
import NoMatch from '../components/NoMatch';
import Header from '../components/Header';
import Footer from '../components/Footer';

// import { createBrowserHistory } from 'history';

/*  Для реализации навигации предлагаю использовать react-router
    доки https://reactrouter.com/
    Навигацию по приложению наверное будем делать через хук useHistory()
    Можно также использовать компонет Link как в примере ниже
    или как указано в доках к MaterialUI https://material-ui.com/guides/composition/#link,

    Example use hook useHistory
  const Button = () => {
  const setHistory = useHistory();
  const hundleRoute = (e) => {
    e.preventDefault();
    setHistory.push('/universities');
  };
  return (
    <button type="button" onClick={hundleRoute}>
      Go Universities
    </button>
  );
};
*/

export default function PageRouter() {
  // const history = createBrowserHistory();
  // Пример: состояние счетчика хранит Redux
  const counter = useSelector((state) => state.counterData);
  const dispatch = useDispatch();
  const hundleInc = (e) => {
    e.preventDefault();
    dispatch(incrementCounter());
  };

  return (
    <Router>
      <Header />

      <button type="button" onClick={hundleInc}>
        Counter=
        {counter}
      </button>
      {/*
          A <Switch> looks through all its children <Route>
          elements and renders the first one whose path
          matches the current URL. Use a <Switch> any time
          you have multiple routes, but you want only one
          of them to render at a time
        */}
      <Switch>
        <Route exact path="/">
          <Main />
        </Route>
        <Route path="/professions">
          <Professions />
        </Route>
        <Route path="/specializations">
          <Specializations />
        </Route>
        <Route path="/universities">
          <Universities />
        </Route>
        <Route path="*">
          <NoMatch />
        </Route>
      </Switch>

      <Footer />
    </Router>
  );
}
