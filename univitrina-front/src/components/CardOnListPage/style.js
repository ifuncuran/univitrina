import { makeStyles } from '@material-ui/core/styles';
import { colorBackground, colorTextLink, largeGap } from '../../style';

const cardHeight = '200px';
const cardBorderRadius = '8px';

const useStyles = makeStyles((theme) => ({
  card: {
    width: '100%',
    marginBottom: largeGap,
    borderRadius: cardBorderRadius,
  },
  card__contentContainer: {
    background: colorBackground,
    height: cardHeight,
    padding: theme.spacing(2),
    borderRadius: cardBorderRadius,
  },

  card__contentContainer_open: {
    borderRadius: `${cardBorderRadius} ${cardBorderRadius} 0 0`,
  },
  card__content: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    textAlign: 'left',
    height: '100%',
  },
  card__title: {},
  card__description: {
    // если описание будет более четырех строчек - обрезаем с многоточием:
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    display: '-webkit-box',
    WebkitLineClamp: '4',
    WebkitBoxOrient: 'vertical',
  },

  card__showSublistLink: {
    color: colorTextLink,
    cursor: 'pointer',
    textDecoration: 'underline',
  },
  card__sublist: {
    background: colorBackground,
    borderRadius: `0 0 ${cardBorderRadius} ${cardBorderRadius}`,
  },
}));

export default useStyles;
