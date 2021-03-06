import { makeStyles } from '@material-ui/core/styles';
import { colorText, largeGap } from '../../style';

const useStyles = makeStyles((theme) => ({
  spacer: { flex: '1' },

  root: {
    boxShadow: 'none',
    backgroundColor: colorText,
    marginTop: largeGap,
  },

  toolbar: {
    paddingLeft: '0',
    paddingRight: '0',
  },

  title: {
    padding: 0,
    [theme.breakpoints.down('sm')]: {
      padding: '20px 0 0 0',
    },
    color: '#ffffff',
    textTransform: 'none',
    fontFamily: 'Raleway, Arial, sans-serif',
    fontSize: '20px',
    fontStyle: 'normal',
    fontWeight: '600',
    '&:hover': {
      boxShadow: 'none',
      backgroundColor: 'inherit',
    },
  },

  copyright: {
    [theme.breakpoints.down('sm')]: {
      padding: '0 0 25px 0',
    },
    color: '#ffffff',
    textTransform: 'none',
    fontSize: '12px',
  },

  gridFooter: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
  },

  gridCopyright: {
    display: 'flex',
    [theme.breakpoints.up('md')]: {
      flexDirection: 'row-reverse',
    },
  },

  gridTitle: {
    display: 'flex',
  },
}));

export default useStyles;
