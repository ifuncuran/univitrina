import { makeStyles } from '@material-ui/core/styles';
import { colorBackground, colorTextLink, largeGap } from '../../style';

const universityCardHeight = '200px';
const cardBorderRadius = '8px';

const useStyles = makeStyles((theme) => ({
  universityCard: {
    width: '100%',
    marginBottom: largeGap,
    borderRadius: cardBorderRadius,
  },
  universityCard__contentContainer: {
    background: colorBackground,
    height: universityCardHeight,
    padding: theme.spacing(2),
    borderRadius: cardBorderRadius,
  },

  universityCard__contentContainer_open: {
    borderRadius: `${cardBorderRadius} ${cardBorderRadius} 0 0`,
  },
  universityCard__content: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    textAlign: 'left',
    height: '100%',
  },
  universityCard__title: {},
  universityCard__description: {
    // если описание будет более четырех строчек - обрезаем с многоточием:
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    display: '-webkit-box',
    WebkitLineClamp: '4',
    WebkitBoxOrient: 'vertical',
  },

  universityCard__showSpecialitiesLink: {
    color: colorTextLink,
    cursor: 'pointer',
    textDecoration: 'underline',
  },
  universityCard__SpecialitiesList: {
    background: colorBackground,
    borderRadius: `0 0 ${cardBorderRadius} ${cardBorderRadius}`,
  },
}));

export default useStyles;
