import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  container: {
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(1),
    [theme.breakpoints.up('md')]: {
      paddingLeft: theme.spacing(4),
      paddingRight: theme.spacing(4),
    },
  },
}));

export default useStyles;
