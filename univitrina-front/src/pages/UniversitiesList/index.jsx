import React, { useEffect, useState, useCallback } from 'react';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography } from '@material-ui/core';
import { getFilteredUniversitiesList } from '../../common/getUniversitiesData';
import getUniversitiesParametersVariants from '../../common/getParametersVariants';
import UniversityCard from '../../components/UniversityCard';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';
import MainContainer from '../../components/MainContainer';
import FilterOnListPage from '../../components/FilterOnListPage';
import declensionByNumber from '../../common/declensionByNumber';

function dynamicTitle(numberOfFound, searchString) {
  let numberOfFoundString = '';
  let searchStringAddition = ' ';
  let endOfOutput = '';

  if (numberOfFound !== 0) {
    numberOfFoundString = `${numberOfFound} 
      ${declensionByNumber(numberOfFound, [
        'университет',
        'университета',
        'университетов',
      ])}`;
    endOfOutput = ':';
  } else numberOfFoundString = 'Ничего не найдено';

  if (searchString) {
    searchStringAddition = ` по запросу "${searchString}"`;
  }

  return `${numberOfFoundString} ${searchStringAddition} ${endOfOutput}`;
}

const queryUrlParameterNames = ['speciality', 'area', 'text'];
const queryParameterText = ['Специальность', 'Город'];
const path = '/universities';

function UniversitiesList() {
  const classes = useStyles();

  const isNotMobile = useIsNotMobie();
  const spacing = isNotMobile ? 4 : 2;

  const UrlSearch = useLocation().search;

  const [query, setQuery] = useState(new URLSearchParams(useLocation().search));
  const [queryParameters, setQueryParameters] = useState([]);

  useEffect(() => {
    setQuery(new URLSearchParams(UrlSearch));
  }, [UrlSearch]);

  useEffect(() => {
    setQueryParameters(queryUrlParameterNames.map((name) => query.get(name)));
  }, [query]);

  const [parametersVariants, setParametersVariants] = useState({});

  const getParametersVariants = useCallback(async () => {
    const response = await getUniversitiesParametersVariants();
    setParametersVariants(response);
  }, []);

  useEffect(() => {
    getParametersVariants();
  }, [getParametersVariants]);

  const [filterUniversities, setFilterUniversities] = useState([]);

  const getFilterUniversities = useCallback(async () => {
    const response = await getFilteredUniversitiesList(
      queryUrlParameterNames,
      queryParameters
    );
    setFilterUniversities(response);
  }, [queryParameters]);

  useEffect(() => {
    getFilterUniversities();
  }, [getFilterUniversities, parametersVariants]);

  return (
    <>
      <div>
        <p>Поиск идёт по универам по url параметру text {query.get('text')}</p>
      </div>
      <MainContainer fixed={isNotMobile}>
        <Typography className={classes.title} variant="h2">
          {dynamicTitle(filterUniversities.length, query.get('text'))}
        </Typography>
        <Grid container justify="flex-end" spacing={spacing}>
          <Grid item xs="auto" sm={12} md={4} lg={4}>
            <Box className={classes.filter}>
              {queryUrlParameterNames
                .filter((urlParameterName) => urlParameterName !== 'text')
                .map((urlParameterName, index) => (
                  <FilterOnListPage
                    key={urlParameterName}
                    urlParameterName={urlParameterName}
                    queryParameterValue={queryParameters[index]}
                    queryParameterText={queryParameterText[index]}
                    pagePath={path}
                    parametersVariants={parametersVariants}
                    query={query}
                  />
                ))}
            </Box>
          </Grid>
          <Grid item xs="auto" sm={12} md={8} lg={8}>
            {filterUniversities.map(
              ({ title, description, id, specializationList }) => (
                <UniversityCard
                  key={id}
                  id={id}
                  specializationList={specializationList}
                  title={title}
                  description={description}
                />
              )
            )}
          </Grid>
        </Grid>
      </MainContainer>
    </>
  );
}

export default UniversitiesList;
