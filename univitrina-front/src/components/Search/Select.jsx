import React, { useRef, useEffect, useMemo } from 'react';
import { Box, TextField, InputAdornment, MenuItem } from '@material-ui/core';
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';
import PropTypes from 'prop-types';
import useStyles from './style';
import useToggleState from '../../hooks/useToggleState';

const Select = (props) => {
  const wrapperRef = useRef(null);
  const classes = useStyles();
  const [stateMenu, toggleState, setStateForce] = useToggleState(false);
  const { directions, setDirections } = props;
  const valueSelect = directions[0].name;

  const directionItemList = useMemo(
    () =>
      directions.map((element, index) => {
        const handleChooseValue = (event) => {
          event.preventDefault();
          const modifedDirections = [
            element,
            ...directions.filter(({ name }) => name !== element.name),
          ];
          toggleState();
          setDirections(modifedDirections);
        };

        const isNeedIcon = index === 0;
        return (
          <MenuItem
            key={element.value}
            onClick={handleChooseValue}
            className={classes.selectItem}
            value={element.value}
          >
            {element.name}
            {isNeedIcon && (
              <InputAdornment position="end">
                <KeyboardArrowUpIcon className={classes.selectIcon} />
              </InputAdornment>
            )}
          </MenuItem>
        );
      }),
    [
      classes.selectItem,
      classes.selectIcon,
      setDirections,
      directions,
      toggleState,
    ]
  );
  useEffect(() => {
    /**
     Селект закрывается при клике вне элемента
     */
    function handleClickOutside(event) {
      if (wrapperRef.current && !wrapperRef.current.contains(event.target)) {
        setStateForce(false);
      }
    }
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [wrapperRef, setStateForce]);
  const menuDisplayProprty = stateMenu ? 'block' : 'none';
  return (
    <Box className={classes.selectBox} ref={wrapperRef}>
      <Box className={classes.selectWrapper}>
        <TextField
          variant="outlined"
          readOnly
          onClick={toggleState}
          className={classes.selectRoot}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">
                <KeyboardArrowDownIcon className={classes.selectIcon} />
              </InputAdornment>
            ),
            className: classes.selectInput,
          }}
          value={valueSelect}
        />
        <Box
          display={menuDisplayProprty}
          className={classes.selectWrapperMenuList}
        >
          <Box className={classes.selectMenuList}>{directionItemList}</Box>
        </Box>
      </Box>
    </Box>
  );
};

Select.defaultProps = {
  directions: [],
  setDirections: null,
};

Select.propTypes = {
  directions: PropTypes.arrayOf(PropTypes.object),
  setDirections: PropTypes.func,
};

export default Select;
