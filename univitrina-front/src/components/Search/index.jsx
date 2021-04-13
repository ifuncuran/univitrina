import React from 'react';
import { Button, Grid, Paper, Typography } from '@material-ui/core';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';
import Suggest from './Suggest';
import Select from './Select';
import MainContainer from '../MainContainer';

export default () => {
  const classes = useStyles();
  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;
  const university = isNotMobile ? 'университетах' : 'ВУЗах';
  return (
    <section className={classes.root}>
      <Paper className={classes.paper}>
        <MainContainer
          className={classes.container}
          fixed={isNotMobile}
          spacing={spacing}
        >
          <Grid container>
            <Grid item md={10} lg={11}>
              <Typography className={classes.h1} variant="h1">
                Поиск выcшего образования в&nbsp;{university} России
              </Typography>
            </Grid>
            <Grid item sm={10} md={12}>
              <Typography className={classes.h6}>
                Поиск выcшего образования в вузах России
              </Typography>
            </Grid>
            <Grid item sm={12} lg={12}>
              <form className={classes.formSearch}>
                <Grid container>
                  <Grid item sm={12} md={3}>
                    <Select />
                  </Grid>
                  <Grid item sm={12} md={7} lg={5}>
                    <Suggest />
                  </Grid>
                  <Grid
                    className={classes.gridSubmitBtn}
                    item
                    sm={12}
                    md={2}
                    lg={3}
                  >
                    <Button
                      className={classes.submitBtn}
                      variant="contained"
                      color="primary"
                    >
                      Найти
                    </Button>
                  </Grid>
                </Grid>
              </form>
            </Grid>
          </Grid>
        </MainContainer>
      </Paper>
    </section>
  );
};
