import { Typography } from '@material-ui/core';
import React from 'react';
import { useParams } from 'react-router-dom';
import SpecializationCard from '../../components/SpecializationCard';
import UniversityInfo from '../../components/UniversityInfo';
import useIsNotMobie from '../../hooks/useIsNotMobile/useIsNotMobile';
import MainContainer from '../../components/MainContainer';
import useStyles from './style';
import getUniversitiesData from '../../common/getUniversitiesData';

function listCards(specializationList) {
  return specializationList.map(({ id, name, code, description }) => (
    <SpecializationCard
      key={id}
      specializationId={id}
      title={name}
      code={code}
      description={description}
    />
  ));
}

function UniversityPage() {
  const classes = useStyles();

  const isNotMobile = useIsNotMobie();

  const { id } = useParams();
  const UniversityData = getUniversitiesData(id);

  const SpecializationList = listCards(UniversityData.specializationList);

  return (
    <section position="static">
      <UniversityInfo
        title={UniversityData.title}
        description={UniversityData.description}
        extendedDescription={UniversityData.extendedDescription}
      />
      <MainContainer fixed={isNotMobile}>
        <Typography className={classes.title} variant="h2">
          Специальности
        </Typography>
        {SpecializationList}
      </MainContainer>
    </section>
  );
}

export default () => <UniversityPage />;
