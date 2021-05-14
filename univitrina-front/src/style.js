import { createMuiTheme } from '@material-ui/core/styles';
import createBreakpoints from '@material-ui/core/styles/createBreakpoints';

// глобальные переменные стиля, если будет много вынесем в отдельный файл
export const colorText = '#023047';
export const colorActionHover = '#F57C0B';
export const colorAction = '#FFB703';
export const colorBackground = '#F3F2F2';
export const colorTextLink = '#219EBC';

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
    body1: {
      color: colorText,
      fontSize: '16px',
      fontWeight: '500',
    },
    h3: {
      color: colorText,
      fontSize: '22px',
      fontWeight: '600',
      textDecoration: 'none',
    },
  },
};
const theme = createMuiTheme(myTheme, breakpointsFull);

export default theme;
