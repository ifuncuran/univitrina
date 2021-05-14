import { Typography, Grid, Button } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React, { useState, useCallback, useMemo } from 'react';
import PropTypes from 'prop-types';
import Collapse from '@material-ui/core/Collapse';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Box from '@material-ui/core/Box';
import classNames from 'classnames';
import useStyles from './style';
import declensionByNumber from '../../common/declensionByNumber';

const ProfessionsBySpecializationExampleData = [
  {
    specializationId: 1,
    professionsList: [
      {
        id: 1,
        name: 'Аналитик данных',
      },
      {
        id: 2,
        name: 'Криптограф',
      },
    ],
  },
  {
    specializationId: 2,
    professionsList: [
      {
        id: 3,
        name: 'Программист',
      },
      {
        id: 4,
        name: 'Консультант по SAP',
      },
    ],
  },
  {
    specializationId: 3,
    professionsList: [
      {
        id: 5,
        name: 'Инженер-технолог',
      },
    ],
  },
];

// в этой функции будем доставать данные с бэка, а пока заглушка:
function getProfessionsBySpecialization(id) {
  return ProfessionsBySpecializationExampleData.filter(
    (specialization) => Number(specialization.specializationId) === Number(id)
  )[0].professionsList;
}

function SpecializationCard({ specializationId, title, code, description }) {
  const classes = useStyles();

  const [isOpen, setIsOpen] = useState(false);

  const handleClick = useCallback(() => {
    setIsOpen((currentState) => !currentState);
  }, []);

  const professionsBySpecialization = getProfessionsBySpecialization(
    specializationId
  );

  const countProfessions = professionsBySpecialization.length;

  const ListProfessions = useMemo(
    () =>
      professionsBySpecialization.map(({ id, name }) => (
        <ListItem
          button
          component={Link}
          to={`/professions/${id.toString()}`}
          key={id}
        >
          <ListItemText primary={name} />
        </ListItem>
      )),
    [professionsBySpecialization]
  );

  return (
    <Grid
      container
      key={specializationId}
      className={classes.specializationCard}
      justify="flex-end"
    >
      <Grid
        item
        xs="auto"
        sm={12}
        md={4}
        lg={4}
        className={classes.specializationCard__imageContainer}
      >
        <Button
          disableRipple
          color="inherit"
          to={`/specializations/${specializationId.toString()}`}
          component={Link}
          className={classes.specializationCard__image}
          style={{
            backgroundImage: `url(/images/studying/${
              specializationId % 5
            }.jpg)`,
          }}
        />
      </Grid>

      <Grid
        item
        xs="auto"
        sm={12}
        md={8}
        lg={8}
        className={classNames(classes.specializationCard__contentContainer, {
          [classes.specializationCard__contentContainer_open]: isOpen,
        })}
      >
        <Box className={classes.specializationCard__content}>
          <Typography
            to={`/specializations/${specializationId.toString()}`}
            component={Link}
            variant="h3"
            className={classes.specializationCard__title}
          >
            {`${title} - ${code}`}
          </Typography>
          <Typography className={classes.specializationCard__description}>
            {description}
          </Typography>

          <Typography
            className={classes.specializationCard__showProfessionsLink}
            onClick={handleClick}
          >
            {isOpen
              ? 'Скрыть профессии'
              : `${countProfessions} ${declensionByNumber(countProfessions, [
                  'профессия',
                  'профессии',
                  'профессий',
                ])}`}
          </Typography>
        </Box>
      </Grid>
      <Grid
        className={classes.specializationCard__ProfessionsList}
        item
        xs="auto"
        sm={12}
        md={8}
        lg={8}
      >
        <Collapse in={isOpen} timeout="auto" unmountOnExit>
          <List disablePadding>{ListProfessions}</List>
        </Collapse>
      </Grid>
    </Grid>
  );
}

SpecializationCard.propTypes = {
  specializationId: PropTypes.number,
  title: PropTypes.string,
  code: PropTypes.string,
  description: PropTypes.string,
};
SpecializationCard.defaultProps = {
  specializationId: 1,
  title: '',
  code: '',
  description: '',
};

export default SpecializationCard;
