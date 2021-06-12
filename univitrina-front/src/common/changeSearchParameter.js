import 'url-search-params-polyfill';

export default function deleteSearchParameter(
  query,
  parameterName,
  parameterValue
) {
  const changedQuery = new URLSearchParams(query);
  changedQuery.delete(parameterName, parameterValue);

  return changedQuery;
}

export function addSearchParameter(query, parameterName, parameterValue) {
  const changedQuery = new URLSearchParams(query);
  changedQuery.append(parameterName, parameterValue);

  return changedQuery;
}
