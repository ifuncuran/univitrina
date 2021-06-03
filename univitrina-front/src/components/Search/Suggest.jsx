import React from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { Box } from '@material-ui/core';
import useStyles from './style';

function Suggest(props) {
  const classes = useStyles();
  const { inputValue, setInputValue, suggests } = props;

  return (
    <Autocomplete
      freeSolo
      value={inputValue}
      onChange={(event, newValue) => {
        setInputValue(newValue);
      }}
      inputValue={inputValue}
      onInputChange={(event, newInputValue) => {
        setInputValue(newInputValue);
      }}
      onOpen={() => {
        setInputValue(inputValue);
      }}
      classes={{
        root: classes.suggest,
        input: classes.suggestInput,
        option: classes.suggestOption,
      }}
      disableClearable
      options={suggests.map((option) => option.name)}
      renderOption={(option) => (
        <Box style={{ overflow: 'hidden' }}>{option}</Box>
      )}
      renderInput={(params) => (
        <TextField
          /* eslint-disable-next-line react/jsx-props-no-spreading */
          {...params}
          className={classes.suggestTextField}
          margin="normal"
          variant="outlined"
          InputProps={{
            ...params.InputProps,
            className: classes.suggestInput,
          }}
        />
      )}
    />
  );
}

Suggest.defaultProps = {
  value: null,
  setValue: null,
  inputValue: null,
  setInputValue: null,
  suggests: null,
};

Suggest.propTypes = {
  value: PropTypes.string,
  setValue: PropTypes.func,
  inputValue: PropTypes.string,
  setInputValue: PropTypes.func,
  suggests: PropTypes.arrayOf(PropTypes.object),
};

export default Suggest;
