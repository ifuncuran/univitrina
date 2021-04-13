import React from 'react';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import useStyles from './style';

// example
const top100Films = [
  { title: 'The Shawshank Redemption', year: 1994 },
  { title: 'The Godfather', year: 1972 },
  { title: 'The Godfather: Part II', year: 1974 },
  { title: 'The Dark Knight', year: 2008 },
  { title: '12 Angry Men', year: 1957 },
  { title: "Schindler's List", year: 1993 },
  { title: 'Pulp Fiction', year: 1994 },
  { title: 'The Lord of the Rings: The Return of the King', year: 2003 },
  { title: 'The Good, the Bad and the Ugly', year: 1966 },
  { title: 'Fight Club', year: 1999 },
  { title: 'The Lord of the Rings: The Fellowship of the Ring', year: 2001 },
];

export default function FreeSolo() {
  const classes = useStyles();
  return (
    <Autocomplete
      freeSolo
      classes={{
        root: classes.suggest,
        input: classes.suggestInput,
        option: classes.suggestOption,
      }}
      disableClearable
      options={top100Films.map((option) => option.title)}
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
