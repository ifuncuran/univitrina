import { Typography, Toolbar, Grid, Button } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React from 'react';
import { useTheme } from '@material-ui/core/styles';
import useMediaQuery from '@material-ui/core/useMediaQuery';
import useStyles from './style';
import MainContainer from '../MainContainer';

function Footer() {
  const classes = useStyles();
  const theme = useTheme();
  const isNotMobile = useMediaQuery(theme.breakpoints.up('md'));
  const spacing = isNotMobile ? 4 : 2;
  return (
    <footer className={classes.root} position="static">
      <MainContainer fixed={isNotMobile}>
        <Toolbar className={classes.toolbar}>
          <Grid container spacing={spacing} className={classes.gridFooter}>
            <Grid
              className={classes.gridTitle}
              item
              xs="auto"
              sm={12}
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
              className={classes.gridCopyright}
              item
              xs="auto"
              sm={12}
              md={7}
              lg={9}
            >
              <Typography className={classes.copyright}>
                HeadHunter School 2021
              </Typography>
            </Grid>
          </Grid>
        </Toolbar>
      </MainContainer>
    </footer>
  );
}

export default Footer;
