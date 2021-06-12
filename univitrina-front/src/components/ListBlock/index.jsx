import React, { useEffect, useState, useCallback } from 'react';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography } from '@material-ui/core';
import CardOnListPage from '../CardOnListPage';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import useStyles from './style';
import MainContainer from '../MainContainer';
import FilterOnListPage from '../FilterOnListPage';
import dynamicTitle from '../../common/dynamicTitle';

function DropDownList(params) {
  const { list, path, sublistPath, sublistDeclension } = params;
  if (path === '/professions')
    return list.map(
      ({ name, description, professionId, specializationList }) => (
        <CardOnListPage
          key={professionId}
          id={professionId}
          sublist={specializationList}
          title={name}
          description={description}
          pagePath={path}
          sublistPath={sublistPath}
          sublistDeclension={sublistDeclension}
        />
      )
    );
  if (path === '/specializations')
    return list.map(
      ({ name, description, specializationId, professionsList }) => (
        <CardOnListPage
          key={specializationId}
          id={specializationId}
          sublist={professionsList}
          title={name}
          description={description}
          pagePath={path}
          sublistPath={sublistPath}
          sublistDeclension={sublistDeclension}
        />
      )
    );
  if (path === '/universities')
    return list.map(({ title, description, id, specializationList }) => (
      <CardOnListPage
        key={id}
        id={id}
        sublist={specializationList}
        title={title}
        description={description}
        pagePath={path}
        sublistPath={sublistPath}
        sublistDeclension={sublistDeclension}
      />
    ));
  return <></>;
}

function ListBlock(params) {
  const {
    queryUrlParameterNames,
    queryParameterText,
    path,
    declensionList,
    sublistPath,
    sublistDeclension,
    getFilteredListRequest,
    getParametersVariantsRequest,
  } = params;

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
  }, [query, queryUrlParameterNames]);

  const [parametersVariants, setParametersVariants] = useState({});

  const getParametersVariants = useCallback(async () => {
    const response = await getParametersVariantsRequest();
    setParametersVariants(response);
  }, [getParametersVariantsRequest]);

  useEffect(() => {
    getParametersVariants();
  }, [getParametersVariants]);

  const [filterList, setFilterList] = useState([]);

  const getFilterList = useCallback(async () => {
    const response = await getFilteredListRequest(
      queryUrlParameterNames,
      queryParameters
    );
    setFilterList(response);
  }, [queryParameters, getFilteredListRequest, queryUrlParameterNames]);

  useEffect(() => {
    getFilterList();
  }, [getFilterList, parametersVariants]);

  return (
    <>
      <div>
        <p>Поиск идёт по универам по url параметру text {query.get('text')}</p>
      </div>
      <MainContainer fixed={isNotMobile}>
        <Typography className={classes.title} variant="h2">
          {dynamicTitle(filterList.length, query.get('text'), declensionList)}
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
            <DropDownList
              list={filterList}
              path={path}
              sublistPath={sublistPath}
              sublistDeclension={sublistDeclension}
            />
          </Grid>
        </Grid>
      </MainContainer>
    </>
  );
}

export default ListBlock;
