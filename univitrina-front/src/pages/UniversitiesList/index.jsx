import React from 'react';
import { getFilteredUniversitiesList } from '../../common/getUniversitiesData';
import getUniversitiesParametersVariants from '../../common/getParametersVariants';
import ListBlock from '../../components/ListBlock';

const queryUrlParameterNames = ['speciality', 'area', 'text'];
const queryParameterText = ['Специальность', 'Город'];
const path = '/universities';
const declensionList = ['университет', 'университета', 'университетов'];
const sublistPath = '/specializations';
const sublistDeclension = ['специальность', 'специальности', 'специальностей'];

function UniversitiesList() {
  return (
    <ListBlock
      queryUrlParameterNames={queryUrlParameterNames}
      queryParameterText={queryParameterText}
      path={path}
      declensionList={declensionList}
      sublistPath={sublistPath}
      sublistDeclension={sublistDeclension}
      getFilteredListRequest={getFilteredUniversitiesList}
      getParametersVariantsRequest={getUniversitiesParametersVariants}
    />
  );
}

export default UniversitiesList;
