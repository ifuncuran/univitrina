import React from 'react';
import { Box, Grid, Button, Paper, Typography } from '@material-ui/core';
// import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';
import MainContainer from '../MainContainer';

const Description = (props) => {
  const { name, description, id } = props;
  const classes = useStyles();
  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;
  const href = `https://hh.ru/search/vacancy?area=3&fromSearchLine=true&st=searchVacancy&text=${name}`;
  return (
    <section className={classes.root}>
      <Paper className={classes.paper}>
        <MainContainer fixed={isNotMobile} spacing={spacing}>
          <Grid container>
            <Grid container item sm={12} md={8} lg={9}>
              <Grid item md={10} lg={11}>
                <Typography className={classes.title} variant="h6">
                  Профессия:
                </Typography>
              </Grid>
              <Grid item sm={10} md={12}>
                <Typography className={classes.name}>{name}</Typography>
              </Grid>
              {/*
              <Grid item sm={10} md={12}>
                <Typography className={classes.fork}>
                  Зарплатная вилка: 120 000 - 140 000р
                </Typography>
              </Grid>
              */}
              <Grid item sm={12} md={11} lg={10}>
                <Typography className={classes.description}>
                  {description}
                </Typography>
              </Grid>
              <Grid className={classes.gridSubmitBtn} item>
                {/* Пока не знаю параметры фильтра на страницу списка вузов, временный линк на полный список вузов */}
                <Button
                  className={classes.submitBtn}
                  variant="contained"
                  color="primary"
                  href={href}
                  key={id}
                >
                  Смотреть вакансии
                </Button>
              </Grid>
            </Grid>
            <Grid item sm={false} md={4} lg={3}>
              <Box className={classes.image} />
            </Grid>
          </Grid>
        </MainContainer>
      </Paper>
    </section>
  );
};

Description.defaultProps = {
  description: 'нет данных',
  name: 'нет данных',
  id: null,
};

Description.propTypes = {
  description: PropTypes.string,
  name: PropTypes.string,
  id: PropTypes.number,
};

export default Description;
