import { useTheme } from '@material-ui/core/styles';
import useMediaQuery from '@material-ui/core/useMediaQuery';

function useIsNotMobile() {
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.up('md'));
  return isMobile;
}

export default useIsNotMobile;
