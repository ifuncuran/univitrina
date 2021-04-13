import { makeStyles } from '@material-ui/core/styles';
import background from './Rectangle29.jpg';
import { colorDarkYellow, colorYellow } from '../../style';

const titleColor = '#ffffff';

const heightOfFormElementsSm = 51;
const heightOfFormElementsMd = 39;
const heightOfFormElementsLg = 53;

const fontSizeForSelectSm = '16px';
const fontSizeForSelectMd = '14px';
const fontSizeForSelectLg = '16px';

const borderRadiusOnlyTheTop = '8px 8px 0px 0px';
const borderRadiusOnlyTheLeft = '8px 0px 0px 8px';
const borderRadiusOnlyTheRight = '0px 8px 8px 0px';
const borderRadiusOnlyTheBottom = '0px 0px 8px 8px';

const paddingForSelectElementsSm = '0px 10px 0px 15px';
const paddingForSelectElementsMd = '0px 8px 0px 12px';
const paddingForSelectElementsLg = '0px 16px 0px 20px';

const fontSizeForSuggestSm = '14px';
const fontSizeForSuggestLg = '15px';

const useStyles = makeStyles((theme) => ({
  root: {
    boxShadow: 'none',
  },

  paper: {
    backgroundImage: `url(${background})`,
    backgroundPosition: 'center',
    backgroundSize: 'cover',
  },

  h1: {
    textAlign: 'left',
    color: titleColor,
    fontSize: '30px',
    marginTop: '55px',
    fontWeight: '700',
    fontStyle: 'normal',
    [theme.breakpoints.up('md')]: {
      fontSize: '36px',
      marginTop: '95px',
    },
    [theme.breakpoints.up('lg')]: {
      fontSize: '50px',
      marginTop: '130px',
    },
  },

  h6: {
    textAlign: 'left',
    color: titleColor,
    fontSize: '14px',
    marginTop: '10px',
    fontWeight: '500',
    fontStyle: 'normal',
    [theme.breakpoints.up('md')]: {
      fontSize: '16px',
      marginTop: '20px',
    },
    [theme.breakpoints.up('lg')]: {
      marginTop: '30px',
    },
  },

  formSearch: {
    marginTop: '25px',
    marginBottom: '75px',
    [theme.breakpoints.up('md')]: {
      marginTop: '45px',
      marginBottom: '130px',
    },
    [theme.breakpoints.up('lg')]: {
      marginTop: '55px',
      marginBottom: '175px',
    },
  },

  selectBox: {
    fontWeight: '600',
    textAlign: 'left',
  },

  selectInput: {
    cursor: 'pointer',
    padding: paddingForSelectElementsSm,
    backgroundColor: titleColor,
    fontSize: fontSizeForSelectSm,
    height: `${heightOfFormElementsSm}px`,
    borderRadius: borderRadiusOnlyTheTop,
    [theme.breakpoints.up('md')]: {
      fontSize: fontSizeForSelectMd,
      height: `${heightOfFormElementsMd}px`,
      borderRadius: borderRadiusOnlyTheLeft,
      padding: paddingForSelectElementsMd,
    },
    [theme.breakpoints.up('lg')]: {
      height: `${heightOfFormElementsLg}px`,
      fontSize: fontSizeForSelectLg,
      borderRadius: borderRadiusOnlyTheLeft,
      padding: paddingForSelectElementsLg,
    },
    '&:focus': {
      backgroundColor: titleColor,
      borderRadius: 'inherit',
      outline: '0',
      outlineOffset: '0',
    },
    '& .MuiInputBase-input': {
      color: 'transparent',
      textShadow: '0 0 0 black',
      cursor: 'pointer',
      paddingLeft: '0px',
    },
  },

  selectFocuced: {
    outline: '0',
    outlineOffset: '0',
  },

  selectRoot: {
    width: '100%',
    borderRadius: borderRadiusOnlyTheTop,
    [theme.breakpoints.up('md')]: {
      borderRadius: borderRadiusOnlyTheLeft,
    },
    [theme.breakpoints.up('lg')]: {
      borderRadius: borderRadiusOnlyTheLeft,
    },
    '&:focus': {
      outline: '0',
      outlineOffset: '0',
    },
    '&.Mui-focused': {
      outline: '0',
      outlineOffset: '0',
      '& .MuiOutlinedInput-notchedOutline': {
        border: 'none',
      },
    },
  },

  selectWrapper: {
    position: 'relative',
  },

  selectWrapperMenuList: {
    position: 'absolute',
    width: '100%',
    top: `${-heightOfFormElementsSm * 2}px`,
    borderRadius: borderRadiusOnlyTheTop,
    overflow: 'hidden',
    [theme.breakpoints.up('md')]: {
      top: `0px`,
      borderRadius: '8px 0px 8px 8px',
    },
  },

  selectMenuList: {
    display: 'flex',
    flexDirection: 'column-reverse',
    width: '100%',
    backgroundColor: titleColor,
    [theme.breakpoints.up('md')]: {
      flexDirection: 'column',
    },
  },

  selectItem: {
    justifyContent: 'space-between',
    fontSize: fontSizeForSelectSm,
    padding: paddingForSelectElementsSm,
    height: `${heightOfFormElementsSm}px`,
    width: '100%',
    [theme.breakpoints.up('md')]: {
      height: `${heightOfFormElementsMd}px`,
      fontSize: fontSizeForSelectMd,
      padding: paddingForSelectElementsMd,
    },
    [theme.breakpoints.up('lg')]: {
      height: `${heightOfFormElementsLg}px`,
      fontSize: fontSizeForSelectLg,
      padding: paddingForSelectElementsLg,
    },
    '&:hover': {
      backgroundColor: colorYellow,
    },
  },

  selectIcon: {
    fontSize: '34px',
    [theme.breakpoints.up('md')]: {
      fontSize: '18px',
    },
    [theme.breakpoints.up('lg')]: {
      fontSize: '25px',
    },
  },

  suggest: {
    marginTop: '2px',
    fontSize: fontSizeForSuggestSm,
    [theme.breakpoints.up('md')]: {
      marginTop: '0',
      marginLeft: '2px',
    },
  },

  suggestTextField: {
    width: '100%',
    marginTop: 0,
    marginButtom: 0,
    backgroundColor: titleColor,
    borderRadius: borderRadiusOnlyTheBottom,
    [theme.breakpoints.up('md')]: {
      borderRadius: borderRadiusOnlyTheRight,
    },
  },

  suggestInput: {
    height: `${heightOfFormElementsSm}px`,
    borderRadius: borderRadiusOnlyTheBottom,
    fontSize: fontSizeForSuggestSm,
    fontWeight: '500',
    [theme.breakpoints.up('md')]: {
      height: `${heightOfFormElementsMd}px`,
      borderRadius: borderRadiusOnlyTheRight,
    },
    [theme.breakpoints.up('lg')]: {
      height: `${heightOfFormElementsLg}px`,
      fontSize: fontSizeForSuggestLg,
      borderRadius: borderRadiusOnlyTheRight,
    },
  },

  suggestOption: {
    height: `${heightOfFormElementsSm}px`,
    fontSize: fontSizeForSuggestSm,
    fontWeight: '500',
    [theme.breakpoints.up('lg')]: {
      fontSize: fontSizeForSuggestLg,
    },
  },

  submitBtn: {
    width: '100%',
    fontSize: '23px',
    fontWeight: '700',
    textTransform: 'none',
    backgroundColor: colorYellow,
    borderRadius: '8px',
    height: '60px',
    transitionProperty: 'background',
    transitionDuration: '1s',
    transitionTimingFunction: 'ease',
    [theme.breakpoints.up('md')]: {
      height: heightOfFormElementsMd,
      fontSize: '18px',
    },
    [theme.breakpoints.up('lg')]: {
      width: '207px',
      height: heightOfFormElementsLg,
      fontSize: '20px',
    },
    '&:hover': {
      backgroundColor: colorDarkYellow,
    },
  },

  gridSubmitBtn: {
    display: 'flex',
    [theme.breakpoints.up('md')]: {
      paddingLeft: '13px',
    },
  },
}));

export default useStyles;
