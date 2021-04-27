import { createMuiTheme } from '@material-ui/core/styles';
import createBreakpoints from '@material-ui/core/styles/createBreakpoints';

// глобальные переменные стиля, если будет много вынесем в отдельный файл
export const fontColorBlack = '#023047';
export const colorYellow = '#FFB703';

const BREAKPOINTS = {
  xs: 0,
  sm: 320,
  md: 768,
  lg: 1000,
};

const breakpointsFull = {
  breakpoints: createBreakpoints({
    values: BREAKPOINTS,
  }),
};

/* exemple
const myTheme = {
  typography: {
    h6: {
      color: '#023047',
    },
    body1: {
      fontWeight: 500,
    },
    button: {
      fontStyle: 'italic',
    },
  },
};
*/
const myTheme = {
  typography: {
    fontFamily: 'Montserrat, sans-serif',
  },
};
const theme = createMuiTheme(myTheme, breakpointsFull);
export default theme;
