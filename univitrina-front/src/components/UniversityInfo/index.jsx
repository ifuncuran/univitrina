import { Typography, Grid, Box } from '@material-ui/core';
import React from 'react';
import PropTypes from 'prop-types';
import MainContainer from '../MainContainer';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';

function UniversityInfo({ title, description, extendedDescription }) {
  const isNotMobile = useIsNotMobie();
  const classes = useStyles();
  return (
    <Box
      className={classes.root}
      style={{
        overflow: 'hidden',
      }}
    >
      <MainContainer fixed={isNotMobile}>
        <Grid container className={classes.UniversityInfo}>
          <Grid
            item
            xs="auto"
            sm={12}
            md={8}
            lg={8}
            className={classes.UniversityInfo__contentContainer}
          >
            <Typography
              variant="h3"
              className={classes.UniversityInfo__pageHeader}
            >
              Университет:
            </Typography>
            <Typography variant="h2" className={classes.UniversityInfo__title}>
              {title}
            </Typography>
            <Typography className={classes.UniversityInfo__description}>
              {(description, extendedDescription)}
            </Typography>
          </Grid>

          <Grid
            item
            md={4}
            lg={4}
            className={classes.UniversityInfo__imageContainer}
          >
            <img
              src="/images/university/universityMain.png"
              alt="University"
              className={classes.UniversityInfo__image}
            />
          </Grid>
        </Grid>
      </MainContainer>
    </Box>
  );
}

UniversityInfo.propTypes = {
  title: PropTypes.string,
  description: PropTypes.string,
  extendedDescription: PropTypes.string,
};
UniversityInfo.defaultProps = {
  title: '',
  description: '',
  extendedDescription: '',
};

export default UniversityInfo;
