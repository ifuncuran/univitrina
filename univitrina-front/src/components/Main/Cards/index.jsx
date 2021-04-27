import { Typography, Grid, Button, Card } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React from 'react';
import useStyles from './style';
import MainContainer from '../../MainContainer';
import useIsNotMobie from '../../../hooks/useIsNotMobile/useIsNotMobile';

const cardsData = [
  {
    title: 'ВУЗы по регионам России',
    description: 'Поиск высшего образования в\u00A0ВУЗах России',
    backgroundSrc: '/images/img_university.jpg',
    href: '/universities',
  },
  {
    title: 'Список специальностей',
    description: 'Поиск высшего образования в\u00A0ВУЗах России',
    backgroundSrc: '/images/img_styding.jpg',
    href: '/specializations',
  },
  {
    title: 'Список профессий',
    description: 'Поиск высшего образования в\u00A0ВУЗах России',
    backgroundSrc: '/images/img_work.jpg',
    href: '/professions',
  },
];

function Cards() {
  const classes = useStyles();
  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;

  const ListCards = cardsData.map(
    ({ title, description, backgroundSrc, href }) => (
      <Grid key={href} item xs="auto" sm={12} md={4} lg={4}>
        <Card className={classes.gridCard}>
          <Button
            className={classes.card}
            disableRipple
            color="inherit"
            to={href}
            component={Link}
            style={{ backgroundImage: `url(${backgroundSrc})` }}
          />
          <Typography to={href} component={Link} className={classes.title}>
            {title}
          </Typography>
          <Typography
            to={href}
            component={Link}
            className={classes.description}
          >
            {description}
          </Typography>
        </Card>
      </Grid>
    )
  );

  return (
    <section className={classes.root} position="static">
      <MainContainer fixed={isNotMobile}>
        <Grid container spacing={spacing}>
          {ListCards}
        </Grid>
      </MainContainer>
    </section>
  );
}

export default Cards;
