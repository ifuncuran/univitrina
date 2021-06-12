import { Typography, Grid, Button, Card } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React from 'react';
import PropTypes from 'prop-types';
import useStyles from './style';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import MainContainer from '../MainContainer';

function ProfessionCards({ cards }) {
  const classes = useStyles();
  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;

  return (
    <>
      <section className={classes.root} position="static">
        <MainContainer fixed={isNotMobile}>
          <Typography className={classes.title} variant="h2">
            Специальности
          </Typography>
          <Grid container spacing={spacing}>
            {cards.map(({ id, name }) => (
              <Grid key={id} item xs="auto" sm={12} md={4} lg={4}>
                <Card className={classes.gridCard}>
                  <Button
                    className={classes.card}
                    disableRipple
                    color="inherit"
                    to={`/specializations/${id}`}
                    component={Link}
                    style={{
                      backgroundImage: `url(/images/studying/${
                        (id % 5) + 1
                      }.jpg)`,
                    }}
                  />
                  <Typography
                    to={`/specializations/${id}`}
                    component={Link}
                    variant="h3"
                    className={classes.name}
                  >
                    {name}
                  </Typography>
                </Card>
              </Grid>
            ))}
          </Grid>
        </MainContainer>
      </section>
    </>
  );
}

ProfessionCards.propTypes = {
  cards: PropTypes.arrayOf(
    PropTypes.shape({
      professionId: PropTypes.number,
      title: PropTypes.string,
    })
  ),
};

ProfessionCards.defaultProps = {
  cards: [],
};

export default ProfessionCards;
