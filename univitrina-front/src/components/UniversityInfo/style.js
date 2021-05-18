import { makeStyles } from '@material-ui/core/styles';
import {
  colorBackground,
  colorTextPrompt,
  smallGap,
  largeGap,
} from '../../style';

const useStyles = makeStyles((theme) => ({
  root: {
    boxShadow: 'none',
    backgroundColor: colorBackground,
    marginBottom: largeGap,
  },

  UniversityInfo__image: {
    [theme.breakpoints.down('sm')]: {
      display: 'none',
    },
    height: '100%',
  },

  UniversityInfo__contentContainer: {
    textAlign: 'left',
    paddingRight: theme.spacing(4),
    [theme.breakpoints.down('sm')]: {
      paddingRight: 0,
    },
  },

  UniversityInfo__pageHeader: {
    marginTop: largeGap,
    fontWeight: 'bold',
    color: colorTextPrompt,
  },

  UniversityInfo__title: {
    paddingTop: smallGap,
    paddingBottom: smallGap,
  },

  UniversityInfo__description: {
    paddingTop: smallGap,
    marginBottom: largeGap,
  },
}));

export default useStyles;
