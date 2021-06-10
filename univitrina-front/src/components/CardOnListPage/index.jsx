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

function CardOnListPage({
  id,
  title,
  description,
  pagePath,
  sublist,
  sublistPath,
  sublistDeclension,
}) {
  const classes = useStyles();

  const [isOpen, toggleIsOpen] = useToggleState(false);

  const countSublist = sublist.length;

  return (
    <Box className={classes.card}>
      <Box
        className={classNames(classes.card__contentContainer, {
          [classes.card__contentContainer_open]: isOpen,
        })}
      >
        <Box className={classes.card__content}>
          <Typography
            to={`${pagePath}/${id}`}
            component={Link}
            variant="h3"
            className={classes.card__title}
          >
            {title}
          </Typography>
          <Typography className={classes.card__description}>
            {description}
          </Typography>

          <Typography
            className={classes.card__showSublistLink}
            onClick={toggleIsOpen}
          >
            {isOpen
              ? `Скрыть ${sublistDeclension[1]}`
              : `${countSublist} ${declensionByNumber(
                  countSublist,
                  sublistDeclension
                )}`}
          </Typography>
        </Box>
      </Box>
      <Box className={classes.card__sublist}>
        <Collapse in={isOpen} timeout="auto" unmountOnExit>
          <List disablePadding>
            {sublist.map((item) => (
              <ListItem
                button
                component={Link}
                to={`${sublistPath}/${item.id}`}
                key={item.id}
              >
                <ListItemText primary={item.name} />
              </ListItem>
            ))}
          </List>
        </Collapse>
      </Box>
    </Box>
  );
}

CardOnListPage.propTypes = {
  id: PropTypes.number,
  title: PropTypes.string,
  description: PropTypes.string,
  pagePath: PropTypes.string,
  sublist: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number,
      name: PropTypes.string,
    })
  ),
  sublistPath: PropTypes.string,
  sublistDeclension: PropTypes.arrayOf(PropTypes.string),
};
CardOnListPage.defaultProps = {
  id: 1,
  title: '',
  description: '',
  pagePath: '',
  sublist: [],
  sublistPath: '',
  sublistDeclension: [],
};

export default CardOnListPage;
