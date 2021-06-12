import React, { useCallback, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { Button, debounce, Grid, Paper } from '@material-ui/core';
import { Link } from 'react-router-dom';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';
import Suggest from '../Search/Suggest';
import Select from '../Search/Select';
import MainContainer from '../MainContainer';
import { requestDataForSuggest } from '../../common/request';

function SearchOnListPage({ textToSearch, path }) {
  const classes = useStyles();
  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;

  const [directions, setDirections] = useState([
    { name: 'Специальность', value: '/specializations' },
    { name: 'Профессия', value: '/professions' },
    { name: 'ВУЗ', value: '/universities' },
  ]);

  const firstElement = directions[0];

  const [inputSuggestValue, setSuggestInputValue] = useState('');
  const [suggests, setSuggests] = useState([]);

  useEffect(() => {
    if (textToSearch) setSuggestInputValue(textToSearch);
  }, [textToSearch]);

  useEffect(() => {
    if (path === '/specializations')
      setDirections([
        { name: 'Специальность', value: '/specializations' },
        { name: 'Профессия', value: '/professions' },
        { name: 'ВУЗ', value: '/universities' },
      ]);
    if (path === '/professions')
      setDirections([
        { name: 'Профессия', value: '/professions' },
        { name: 'Специальность', value: '/specializations' },
        { name: 'ВУЗ', value: '/universities' },
      ]);
    if (path === '/universities')
      setDirections([
        { name: 'ВУЗ', value: '/universities' },
        { name: 'Профессия', value: '/professions' },
        { name: 'Специальность', value: '/specializations' },
      ]);
  }, [path]);

  const request = useCallback(
    async (value) => {
      if (value === '') {
        setSuggests([]);
      } else {
        const list = await requestDataForSuggest(value, firstElement.value);
        setSuggests(list);
      }
      setSuggestInputValue(value);
    },
    [firstElement.value]
  );

  const debounceRequest = debounce(request, 50);
  const handleChangeValue = useCallback(debounceRequest, [debounceRequest]);
  const handleChangeSelect = useCallback((value) => {
    setDirections(value);
    setSuggests([]);
  }, []);

  return (
    <section className={classes.root}>
      <Paper className={classes.paper}>
        <MainContainer
          className={classes.container}
          fixed={isNotMobile}
          spacing={spacing}
        >
          <Grid container>
            <Grid item sm={12} lg={12}>
              <form className={classes.formSearch}>
                <Grid container>
                  <Grid item sm={12} md={3}>
                    <Select
                      directions={directions}
                      setDirections={handleChangeSelect}
                    />
                  </Grid>
                  <Grid item sm={12} md={7} lg={5}>
                    <Suggest
                      inputValue={inputSuggestValue}
                      setInputValue={handleChangeValue}
                      suggests={suggests}
                      pagePath={firstElement.value}
                    />
                  </Grid>
                  <Grid
                    className={classes.gridSubmitBtn}
                    item
                    sm={12}
                    md={2}
                    lg={3}
                  >
                    <Button
                      to={`${firstElement.value}?text=${inputSuggestValue}`}
                      component={Link}
                      className={classes.submitBtn}
                      variant="contained"
                      color="primary"
                    >
                      Найти
                    </Button>
                  </Grid>
                </Grid>
              </form>
            </Grid>
          </Grid>
        </MainContainer>
      </Paper>
    </section>
  );
}

SearchOnListPage.propTypes = {
  textToSearch: PropTypes.string,
  path: PropTypes.string,
};
SearchOnListPage.defaultProps = {
  textToSearch: '',
  path: '',
};

export default SearchOnListPage;
