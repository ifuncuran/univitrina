import React from 'react';
import getFilteredProfessionsList from '../../common/getProfessionsData';
import { getProfessionsParametersVariants } from '../../common/getParametersVariants';
import ListBlock from '../../components/ListBlock';

const queryUrlParameterNames = ['speciality', 'text'];
const queryParameterText = ['Специальность'];
const path = '/professions';
const declensionList = ['профессия', 'профессии', 'профессий'];
const sublistPath = '/specializations';
const sublistDeclension = ['специальность', 'специальности', 'специальностей'];

function ProfessionsList() {
  return (
    <ListBlock
      queryUrlParameterNames={queryUrlParameterNames}
      queryParameterText={queryParameterText}
      path={path}
      declensionList={declensionList}
      sublistPath={sublistPath}
      sublistDeclension={sublistDeclension}
      getFilteredListRequest={getFilteredProfessionsList}
      getParametersVariantsRequest={getProfessionsParametersVariants}
    />
  );
}

export default ProfessionsList;
