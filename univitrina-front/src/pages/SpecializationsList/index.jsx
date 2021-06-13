import React from 'react';
import getFilteredSpecializationsList from '../../common/getSpecializationData';
import { getSpecializationsParametersVariants } from '../../common/getParametersVariants';
import ListBlock from '../../components/ListBlock';

const queryUrlParameterNames = [
  'university',
  'profession',
  'training_direction',
  'text',
];
const queryParameterText = ['Университет', 'Профессия', 'Направление обучения'];
const path = '/specializations';
const declensionList = ['специальность', 'специальности', 'специальностей'];
const sublistPath = '/professions';
const sublistDeclension = ['профессия', 'профессии', 'профессий'];

function SpecializationsList() {
  return (
    <ListBlock
      queryUrlParameterNames={queryUrlParameterNames}
      queryParameterText={queryParameterText}
      path={path}
      declensionList={declensionList}
      sublistPath={sublistPath}
      sublistDeclension={sublistDeclension}
      getFilteredListRequest={getFilteredSpecializationsList}
      getParametersVariantsRequest={getSpecializationsParametersVariants}
    />
  );
}

export default SpecializationsList;
