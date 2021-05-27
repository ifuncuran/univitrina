import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import ProfessionsList from '../pages/ProfessionsList';
import SpecializationsList from '../pages/SpecializationsList';
import UniversitiesList from '../pages/UniversitiesList';
import UniversitiesAreas from '../pages/UniversitiesAreas';
import UniversityPage from '../pages/UniversityPage';
import SpecializationPage from '../pages/SpecializationPage';
import ProfessionPage from '../pages/ProfessionPage';
import Main from '../pages/Main';
import NoMatch from '../components/NoMatch';
import Header from '../components/Header';
import Footer from '../components/Footer';

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
  return (
    <Router>
      <Header />
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
        <Route exact path="/professions">
          <ProfessionsList />
        </Route>
        <Route path="/professions/:id">
          <ProfessionPage />
        </Route>
        <Route exact path="/specializations">
          <SpecializationsList />
        </Route>
        <Route path="/specializations/:id">
          <SpecializationPage />
        </Route>
        <Route path="/universities-areas">
          <UniversitiesAreas />
        </Route>
        <Route exact path="/universities">
          <UniversitiesList />
        </Route>
        <Route path="/universities/:id">
          <UniversityPage />
        </Route>
        <Route path="*">
          <NoMatch />
        </Route>
      </Switch>
      <Footer />
    </Router>
  );
}
