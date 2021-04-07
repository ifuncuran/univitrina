import './fonts/fonts.css';
import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { MuiThemeProvider } from '@material-ui/core/styles';
import reducers from './reducers';
import state from './initState';
import './index.css';
import App from './components/App';
import theme from './style';

// import reportWebVitals from './reportWebVitals';

// заготовка Store

// для отладки redux нужен REDUX_DEVTOOLS
// перед выпуском в прод  удалить
// window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()

/* eslint-disable no-underscore-dangle */
const store = createStore(
  reducers,
  state,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);
/* eslint-enable */

ReactDOM.render(
  <React.StrictMode>
    <MuiThemeProvider theme={theme}>
      <Provider store={store}>
        <App />
      </Provider>
    </MuiThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();
