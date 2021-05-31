import React from 'react';
import { useParams } from 'react-router-dom';
import getSpecializationData from '../../common/getSpecializationData';
import ProfessionCards from '../../components/ProfessionCards';

function SpecializationPage() {
  const { id } = useParams();
  const SpecializationData = getSpecializationData(id);

  return (
    <section position="static">
      <p>{`id=${SpecializationData.specializationId}`}</p>
      <p>{`name=${SpecializationData.name}`}</p>
      <p>{`code=${SpecializationData.code}`}</p>
      <p>{`description=${SpecializationData.description}`}</p>
      <ProfessionCards cards={SpecializationData.professionsList} />
    </section>
  );
}

export default () => <SpecializationPage />;
