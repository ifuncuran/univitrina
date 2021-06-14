import declensionByNumber from './declensionByNumber';

export default function dynamicTitle(list, searchString, declensionList) {
  if (list === null) return list;

  const numberOfFound = list.length;
  let numberOfFoundString = '';
  let searchStringAddition = ' ';
  let endOfOutput = '';

  if (numberOfFound !== 0) {
    numberOfFoundString = `${numberOfFound} 
      ${declensionByNumber(numberOfFound, declensionList)}`;
    endOfOutput = ':';
  } else numberOfFoundString = 'Ничего не найдено';

  if (searchString) {
    searchStringAddition = ` по запросу "${searchString}"`;
  }

  return `${numberOfFoundString} ${searchStringAddition} ${endOfOutput}`;
}
