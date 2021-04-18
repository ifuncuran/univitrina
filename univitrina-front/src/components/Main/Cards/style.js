import { makeStyles } from '@material-ui/core/styles';
import { fontColorBlack, colorYellow } from '../../../style';

const CardsContainerPaddingBottom = '80px';
const CardTitlePaddingTop = '10px';
const CardDescriptionPaddingTop = '5px';
const CardMarginTop = '40px';
const TitleFontSize = '26px';
const TitleFontSizeTablet = '20px';
const DescriptionFontSize = '16px';
const DescriptionFontSizeTablet = '13px';

const useStyles = makeStyles((theme) => ({
  root: {
    boxShadow: 'none',
    backgroundColor: '#ffffff',
    paddingBottom: CardsContainerPaddingBottom,
  },

  card: {
    backgroundPosition: 'center',
    backgroundRepeat: 'no-repeat',
    backgroundSize: 'cover',
    backgroundColor: colorYellow,
    borderRadius: '8px',
    height: '200px',
    width: '100%',
    '&:hover': {
      boxShadow: 'none',
    },
  },

  title: {
    fontSize: TitleFontSize,
    [theme.breakpoints.only('md')]: {
      fontSize: TitleFontSizeTablet,
    },
    color: fontColorBlack,
    textDecoration: 'none',
    textTransform: 'none',
    fontWeight: '600',
    paddingTop: CardTitlePaddingTop,
    lineHeight: '1.1',
  },

  description: {
    paddingTop: CardDescriptionPaddingTop,
    fontSize: DescriptionFontSize,
    [theme.breakpoints.only('md')]: {
      fontSize: DescriptionFontSizeTablet,
    },
    [theme.breakpoints.down('sm')]: {
      display: 'none',
    },
    color: fontColorBlack,
    textDecoration: 'none',
    textTransform: 'none',
    fontWeight: '500',
  },

  gridCard: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'start',
    textAlign: 'left',
    marginTop: CardMarginTop,
    boxShadow: 'none',
  },
}));

export default useStyles;
