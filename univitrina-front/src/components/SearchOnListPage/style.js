import { makeStyles } from '@material-ui/core/styles';
import {
  colorActionHover,
  colorAction,
  colorBackground,
  largeGap,
} from '../../style';

const heightOfFormElementsMd = 39;
const heightOfFormElementsLg = 53;

const useStyles = makeStyles(
  (theme) => ({
    root: {
      boxShadow: 'none',
    },

    paper: {
      backgroundColor: colorBackground,
      backgroundPosition: 'center',
      backgroundSize: 'cover',
      borderRadius: 0,
      boxShadow: 'none',
      marginBottom: largeGap,
    },

    formSearch: {
      marginTop: largeGap,
      marginBottom: largeGap,
    },

    submitBtn: {
      width: '100%',
      fontSize: '23px',
      fontWeight: '700',
      textTransform: 'none',
      backgroundColor: colorAction,
      borderRadius: '8px',
      height: '60px',
      transitionProperty: 'background',
      transitionDuration: '1s',
      transitionTimingFunction: 'ease',
      [theme.breakpoints.up('md')]: {
        height: heightOfFormElementsMd,
        fontSize: '18px',
      },
      [theme.breakpoints.up('lg')]: {
        width: '207px',
        height: heightOfFormElementsLg,
        fontSize: '20px',
      },
      '&:hover': {
        backgroundColor: colorActionHover,
      },
    },

    gridSubmitBtn: {
      display: 'flex',
      [theme.breakpoints.up('md')]: {
        paddingLeft: '13px',
      },
    },
  }),
  { index: 1 }
);

export default useStyles;
