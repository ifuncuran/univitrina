import { makeStyles } from '@material-ui/core/styles';
import { colorBackground, colorTextLink, largeGap } from '../../style';

const specializationCardHeight = '200px';
const cardBorderRadius = '8px';

const useStyles = makeStyles((theme) => ({
  specializationCard: {
    width: '100%',
    margin: '0',
    marginBottom: largeGap,
    borderRadius: cardBorderRadius,
  },
  specializationCard__imageContainer: {
    background: colorBackground,
    padding: '0',
    height: specializationCardHeight,
    borderRadius: `${cardBorderRadius} 0 0 ${cardBorderRadius}`,
  },
  specializationCard__image: {
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'cover',
    borderRadius: `${cardBorderRadius} 0 0 ${cardBorderRadius}`,
    height: specializationCardHeight,
    width: '100%',
    [theme.breakpoints.down('sm')]: {
      borderRadius: `${cardBorderRadius} ${cardBorderRadius} 0 0`,
    },
  },
  specializationCard__contentContainer: {
    background: colorBackground,
    height: specializationCardHeight,
    padding: theme.spacing(2),
    borderRadius: `0 ${cardBorderRadius} ${cardBorderRadius} 0`,
    [theme.breakpoints.down('sm')]: {
      borderRadius: `0 0 ${cardBorderRadius} ${cardBorderRadius}`,
    },
  },

  specializationCard__contentContainer_open: {
    borderRadius: `0 ${cardBorderRadius} 0 0`,
    [theme.breakpoints.down('sm')]: {
      borderRadius: '0',
    },
  },
  specializationCard__content: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'space-between',
    textAlign: 'left',
    height: '100%',
  },
  specializationCard__title: {},
  specializationCard__description: {
    // если описание будет более четырех строчек - обрезаем с многоточием:
    overflow: 'hidden',
    textOverflow: 'ellipsis',
    display: '-webkit-box',
    WebkitLineClamp: '4',
    WebkitBoxOrient: 'vertical',
  },

  specializationCard__showProfessionsLink: {
    color: colorTextLink,
    cursor: 'pointer',
    textDecoration: 'underline',
  },
  specializationCard__ProfessionsList: {
    background: colorBackground,
    borderRadius: `0 0 ${cardBorderRadius} ${cardBorderRadius}`,
  },
}));

export default useStyles;
