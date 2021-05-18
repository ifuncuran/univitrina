import { makeStyles } from '@material-ui/core/styles';
import { colorAction, largeGap, smallGap } from '../../style';

const useStyles = makeStyles(() => ({
  root: {
    paddingTop: largeGap,
    paddingBottom: largeGap,
    textAlign: 'left',
  },

  card: {
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'cover',
    backgroundColor: colorAction,
    borderRadius: '8px',
    height: '120px',
    width: '100%',
    '&:hover': {
      boxShadow: 'none',
    },
  },

  title: {
    paddingBottom: largeGap,
  },

  name: {
    paddingTop: smallGap,
  },

  gridCard: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'start',
    textAlign: 'left',
    boxShadow: 'none',
  },
}));

export default useStyles;
