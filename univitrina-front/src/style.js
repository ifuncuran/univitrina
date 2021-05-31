import { createMuiTheme } from '@material-ui/core/styles';
import createBreakpoints from '@material-ui/core/styles/createBreakpoints';

// глобальные переменные стиля, если будет много вынесем в отдельный файл
export const colorText = '#023047';
export const colorActionHover = '#F57C0B';
export const colorAction = '#FFB703';
export const colorBackground = '#F3F2F2';
export const colorTextLink = '#219EBC';
export const colorTextPrompt = 'rgba(2, 48, 71, 0.5)';

export const smallGap = '10px';
export const middleGap = '20px';
export const largeGap = '50px';

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

theme.typography.body1 = {
  fontFamily: 'Montserrat, sans-serif',
  color: colorText,
  fontSize: '14px',
  fontWeight: '500',
  [theme.breakpoints.up('md')]: {
    fontSize: '16px',
  },
};

theme.typography.h3 = {
  fontFamily: 'Montserrat, sans-serif',
  color: colorText,
  fontSize: '22px',
  fontWeight: '600',
  textDecoration: 'none',
};

theme.typography.h2 = {
  fontFamily: 'Montserrat, sans-serif',
  color: colorText,
  fontWeight: 'bold',
  textDecoration: 'none',
  fontSize: '24px',
  lineHeight: '1.1',
  [theme.breakpoints.up('lg')]: {
    fontSize: '30px',
  },
};

export default theme;
