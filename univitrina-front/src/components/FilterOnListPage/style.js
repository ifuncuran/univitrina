import { makeStyles } from '@material-ui/core/styles';
import { colorTextLink } from '../../style';

const useStyles = makeStyles(() => ({
  filter__title: {
    fontWeight: '600',
  },
  filter__listItem: {
    padding: '4px 8px',
  },
  filter__activeParameter: {
    backgroundColor: '#ffffff',
    borderRadius: '8px',
  },
  filter__parameterText: {
    fontSize: '12px',
  },
  filter__showMore: {
    fontSize: '12px',
    color: colorTextLink,
  },
}));

export default useStyles;
