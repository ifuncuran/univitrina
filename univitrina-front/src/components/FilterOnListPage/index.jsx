import { Link } from 'react-router-dom';
import {
  Typography,
  List,
  ListSubheader,
  ListItem,
  ListItemText,
} from '@material-ui/core';
import CancelOutlinedIcon from '@material-ui/icons/CancelOutlined';
import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import classNames from 'classnames';
import useStyles from './style';
import deleteSearchParameter, {
  addSearchParameter,
} from '../../common/changeSearchParameter';
import useToggleState from '../../hooks/useToggleState';

function getSlicedList(list, isOpen, itemsToDisplay) {
  if (list) {
    if (isOpen) {
      return list;
    }
    return list.slice(0, itemsToDisplay);
  }
  return null;
}

function FilterOnListPage({
  urlParameterName,
  queryParameterValue,
  queryParameterText,
  pagePath,
  parametersVariants,
  query,
}) {
  const classes = useStyles();

  const itemsToDisplay = 2;

  const [isReadyToDisplay, setIsReadyToDisplay] = useState(false);
  const [isOpen, toggleIsOpen, setIsOpen] = useToggleState(false);
  const [listToDisplay, setListToDisplay] = useState(
    parametersVariants[urlParameterName]
  );

  useEffect(() => {
    setListToDisplay(
      getSlicedList(
        parametersVariants[urlParameterName],
        isOpen,
        itemsToDisplay
      )
    );
  }, [isOpen, parametersVariants, urlParameterName]);

  useEffect(() => {
    if (Object.keys(parametersVariants).length !== 0 && listToDisplay)
      setIsReadyToDisplay(true);
  }, [parametersVariants, listToDisplay]);

  if (isReadyToDisplay) {
    return (
      <List
        component="nav"
        subheader={
          <ListSubheader
            className={classes.filter__title}
            align="left"
            disableGutters
            disableSticky
          >
            {queryParameterText}
          </ListSubheader>
        }
      >
        {queryParameterValue ? (
          <ListItem
            button
            onClick={() => setIsOpen(false)}
            component={Link}
            to={{
              pathname: pagePath,
              search: `?${deleteSearchParameter(
                query,
                urlParameterName,
                queryParameterValue
              )}`,
            }}
            className={classNames(
              classes.filter__activeParameter,
              classes.filter__listItem
            )}
          >
            <ListItemText>
              <Typography className={classes.filter__parameterText}>
                {
                  parametersVariants[urlParameterName].find(
                    (element) => element.id === Number(queryParameterValue)
                  ).name
                }
              </Typography>
            </ListItemText>
            <CancelOutlinedIcon fontSize="inherit" />
          </ListItem>
        ) : (
          <>
            {listToDisplay.map(({ id, name }) => (
              <ListItem
                key={id}
                button
                component={Link}
                to={{
                  pathname: pagePath,
                  search: `?${addSearchParameter(query, urlParameterName, id)}`,
                }}
                className={classes.filter__listItem}
              >
                <ListItemText>
                  <Typography className={classes.filter__parameterText}>
                    {name}
                  </Typography>
                </ListItemText>
              </ListItem>
            ))}
            {!isOpen &&
            parametersVariants[urlParameterName].length > itemsToDisplay ? (
              <ListItem
                button
                onClick={toggleIsOpen}
                className={classes.filter__listItem}
              >
                <ListItemText>
                  <Typography className={classes.filter__showMore}>
                    {`Показать еще ${
                      parametersVariants[urlParameterName].length -
                      itemsToDisplay
                    }`}
                  </Typography>
                </ListItemText>
              </ListItem>
            ) : null}
          </>
        )}
      </List>
    );
  }
  return null;
}

FilterOnListPage.propTypes = {
  urlParameterName: PropTypes.string,
  queryParameterValue: PropTypes.string,
  queryParameterText: PropTypes.string,
  pagePath: PropTypes.string,
  parametersVariants: PropTypes.shape({
    area: PropTypes.arrayOf(
      PropTypes.shape({
        id: PropTypes.number,
        name: PropTypes.string,
      })
    ),
  }),
  query: PropTypes.instanceOf(URLSearchParams),
};
FilterOnListPage.defaultProps = {
  urlParameterName: '',
  queryParameterValue: '',
  queryParameterText: '',
  pagePath: '',
  parametersVariants: [],
  query: '',
};

export default FilterOnListPage;
