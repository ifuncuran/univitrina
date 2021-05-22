import { Typography, Grid, Button } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React, { useState, useCallback, useMemo, useEffect } from 'react';
import PropTypes from 'prop-types';
import Collapse from '@material-ui/core/Collapse';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Box from '@material-ui/core/Box';
import classNames from 'classnames';
import useStyles from './style';
import declensionByNumber from '../../common/declensionByNumber';
import getSpecializationData from '../../common/getSpecializationData';

function SpecializationCard({ specializationId, title, code, description }) {
  const classes = useStyles();

  const [isOpen, setIsOpen] = useState(false);
  const [
    professionsBySpecialization,
    setProfessionsBySpecialization,
  ] = useState([]);

  const handleClick = useCallback(() => {
    setIsOpen((currentState) => !currentState);
  }, []);

  useEffect(() => {
    setProfessionsBySpecialization(
      getSpecializationData(specializationId).professionsList
    );
  }, [specializationId]);

  const countProfessions = professionsBySpecialization.length;

  const ListProfessions = useMemo(
    () =>
      professionsBySpecialization.map(({ id, name }) => (
        <ListItem button component={Link} to={`/professions/${id}`} key={id}>
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
          to={`/specializations/${specializationId}`}
          component={Link}
          className={classes.specializationCard__image}
          style={{
            backgroundImage: `url(/images/studying/${
              (specializationId % 5) + 1
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
            to={`/specializations/${specializationId}`}
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
