import React from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { useHistory } from 'react-router-dom';
import { Box } from '@material-ui/core';
import useStyles from './style';

function Suggest(props) {
  const classes = useStyles();
  const { inputValue, setInputValue, suggests, pagePath } = props;
  const history = useHistory();

  return (
    <Autocomplete
      freeSolo
      onChange={(event, value) => {
        history.push(`${pagePath}/${value.id}`);
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
      getOptionLabel={(option) => (option.name ? option.name : '')}
      options={suggests}
      renderOption={(option) => (
        <Box style={{ overflow: 'hidden' }}>{option.name}</Box>
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
  inputValue: null,
  setInputValue: null,
  suggests: null,
  pagePath: null,
};

Suggest.propTypes = {
  inputValue: PropTypes.string,
  setInputValue: PropTypes.func,
  suggests: PropTypes.arrayOf(PropTypes.object),
  pagePath: PropTypes.string,
};

export default Suggest;
