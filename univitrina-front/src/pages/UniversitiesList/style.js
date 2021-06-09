import { makeStyles } from '@material-ui/core/styles';
import { colorBackground, middleGap } from '../../style';

const cardBorderRadius = '8px';

const useStyles = makeStyles((theme) => ({
  filter: {
    background: colorBackground,
    padding: theme.spacing(2),
    borderRadius: cardBorderRadius,
  },
  title: {
    textAlign: 'left',
    marginBottom: middleGap,
  },
}));

export default useStyles;
