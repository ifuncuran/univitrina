import { makeStyles } from '@material-ui/core/styles';
import { fontColorBlack } from '../../style';

const useStyles = makeStyles((theme) => ({
  root: {
    boxShadow: 'none',
    backgroundColor: '#FFFFFF',
  },

  container: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
    [theme.breakpoints.up('md')]: {
      paddingLeft: theme.spacing(4),
      paddingRight: theme.spacing(4),
    },
  },

  toolbar: {
    paddingLeft: '0',
    paddingRight: '0',
  },

  menuButton: {
    color: fontColorBlack,
  },

  menuButtonIcon: {
    fontSize: '32px',
  },

  fill: {
    flexGrow: 1,
  },

  title: {
    color: fontColorBlack,
    textTransform: 'none',
    fontFamily: 'Raleway, Arial, sans-serif',
    fontSize: '24px',
    fontStyle: 'normal',
    fontWeight: '600',
    [theme.breakpoints.up('lg')]: {
      fontSize: '27px',
    },
    '&:hover': {
      boxShadow: 'none',
      backgroundColor: 'inherit',
    },
  },

  nav: {
    textAlign: 'left',
  },

  navButton: {
    color: fontColorBlack,
    textTransform: 'none',
    fontStyle: 'normal',
    fontSize: '12px',
    marginLeft: theme.spacing(2),
    [theme.breakpoints.up('lg')]: {
      fontSize: '14px',
      marginLeft: theme.spacing(4),
    },
  },

  gridHeader: {
    display: 'flex',
    alignItems: 'center',
  },

  gridIcon: {
    [theme.breakpoints.down('md')]: {
      display: 'flex',
      flexDirection: 'row-reverse',
    },
  },

  gridNav: {
    marginTop: '3px',
    [theme.breakpoints.up('lg')]: {
      fontSize: '14px',
      marginTop: '5px',
    },
  },

  gridTitle: {
    [theme.breakpoints.down('md')]: {
      display: 'flex',
      alignItems: 'center',
    },
  },
}));

export default useStyles;
