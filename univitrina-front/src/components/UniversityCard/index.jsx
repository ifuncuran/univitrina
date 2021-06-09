import { Typography } from '@material-ui/core';
import { Link } from 'react-router-dom';
import React from 'react';
import PropTypes from 'prop-types';
import Collapse from '@material-ui/core/Collapse';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Box from '@material-ui/core/Box';
import classNames from 'classnames';
import useStyles from './style';
import declensionByNumber from '../../common/declensionByNumber';
import useToggleState from '../../hooks/useToggleState';

function UniversityCard({ id, title, description, specializationList }) {
  const classes = useStyles();

  const [isOpen, toggleIsOpen] = useToggleState(false);

  const countSpecialities = specializationList.length;

  return (
    <Box className={classes.universityCard}>
      <Box
        className={classNames(classes.universityCard__contentContainer, {
          [classes.universityCard__contentContainer_open]: isOpen,
        })}
      >
        <Box className={classes.universityCard__content}>
          <Typography
            to={`/universities/${id}`}
            component={Link}
            variant="h3"
            className={classes.universityCard__title}
          >
            {title}
          </Typography>
          <Typography className={classes.universityCard__description}>
            {description}
          </Typography>

          <Typography
            className={classes.universityCard__showSpecialitiesLink}
            onClick={toggleIsOpen}
          >
            {isOpen
              ? 'Скрыть специализации'
              : `${countSpecialities} ${declensionByNumber(countSpecialities, [
                  'специализация',
                  'специализации',
                  'специализаций',
                ])}`}
          </Typography>
        </Box>
      </Box>
      <Box className={classes.universityCard__SpecialitiesList}>
        <Collapse in={isOpen} timeout="auto" unmountOnExit>
          <List disablePadding>
            {specializationList.map((specialization) => (
              <ListItem
                button
                component={Link}
                to={`/specializations/${specialization.id}`}
                key={specialization.id}
              >
                <ListItemText primary={specialization.name} />
              </ListItem>
            ))}
          </List>
        </Collapse>
      </Box>
    </Box>
  );
}

UniversityCard.propTypes = {
  id: PropTypes.number,
  title: PropTypes.string,
  description: PropTypes.string,
  specializationList: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      name: PropTypes.string,
    })
  ),
};
UniversityCard.defaultProps = {
  id: 1,
  title: '',
  description: '',
  specializationList: [],
};

export default UniversityCard;
