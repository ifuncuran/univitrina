import { makeStyles } from '@material-ui/core/styles';
import background from './Rectangle29.jpg';
import {
  colorActionHover,
  colorAction,
  smallGap,
  middleGap,
  largeGap,
} from '../../style';

const useStyles = makeStyles(() => ({
  root: {
    boxShadow: 'none',
  },

  paper: {
    backgroundColor: '#F3F2F2',
    boxShadow: 'none',
  },

  image: {
    width: '100%',
    backgroundImage: `url(${background})`,
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    height: '100%',
  },

  title: {
    fontSize: '26px',
    fontStyle: 'normal',
    fontWeight: '700',
    lineHeight: '29px',
    letterSpacing: '0em',
    textAlign: 'left',
    color: '#02304766',
    opacity: '0.4',
    marginTop: middleGap,
  },

  name: {
    fontSize: '40px',
    fontStyle: 'normal',
    fontWeight: '700',
    lineHeight: '45px',
    letterSpacing: '0em',
    textAlign: 'left',
    marginTop: smallGap,
  },

  fork: {
    fontSize: '22px',
    fontStyle: 'normal',
    fontWeight: '600',
    lineHeight: '25px',
    letterSpacing: '0em',
    textAlign: 'left',
    marginTop: smallGap,
  },

  description: {
    fontSize: '16px',
    fontStyle: 'normal',
    fontWeight: '500',
    lineHeight: '20px',
    letterSpacing: '0em',
    textAlign: 'left',
    marginTop: smallGap,
  },

  submitBtn: {
    textAlign: 'left',
    width: '220px',
    fontSize: '14px',
    fontWeight: '700',
    textTransform: 'none',
    backgroundColor: colorAction,
    borderRadius: '8px',
    height: '40px',
    transitionProperty: 'background',
    transitionDuration: '1s',
    transitionTimingFunction: 'ease',
    margin: `${largeGap} 0px`,
    '&:hover': {
      backgroundColor: colorActionHover,
    },
  },

  gridSubmitBtn: {},
}));

export default useStyles;
