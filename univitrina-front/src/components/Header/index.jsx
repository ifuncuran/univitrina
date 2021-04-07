import {
  AppBar,
  Container,
  Toolbar,
  IconButton,
  Hidden,
  Button,
  Grid,
} from '@material-ui/core';
import { Link } from 'react-router-dom';
import React from 'react';
import MenuIcon from '@material-ui/icons/Menu';
import { useTheme } from '@material-ui/core/styles';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import useStyles from './style';
import Nav from './HeaderNav';

function Header() {
  const classes = useStyles();
  const theme = useTheme();
  const isNotMobile = useMediaQuery(theme.breakpoints.up('md'));
  const spacing = isNotMobile ? 4 : 2;
  return (
    <>
      <AppBar className={classes.root} position="static">
        <Container className={classes.container} fixed={isNotMobile}>
          <Toolbar className={classes.toolbar}>
            <Grid container spacing={spacing} className={classes.gridHeader}>
              <Grid
                className={classes.gridTitle}
                item
                xs="auto"
                sm={6}
                md={3}
                lg={3}
              >
                <Button
                  className={classes.title}
                  disableRipple
                  color="inherit"
                  to="/"
                  component={Link}
                >
                  Унивитрина
                </Button>
              </Grid>
              <Grid
                item
                className={classes.gridNav}
                xs="auto"
                sm={1}
                md={7}
                lg={6}
              >
                <Nav />
              </Grid>
              <Grid
                className={classes.gridIcon}
                item
                xs="auto"
                sm={5}
                md="auto"
                lg="auto"
              >
                <Hidden only={['xs', 'md', 'lg']}>
                  <IconButton
                    edge="start"
                    color="inherit"
                    area-label="menu"
                    className={classes.menuButton}
                  >
                    <MenuIcon className={classes.menuButtonIcon} />
                  </IconButton>
                </Hidden>
              </Grid>
            </Grid>
          </Toolbar>
        </Container>
      </AppBar>
    </>
  );
}

export default Header;
