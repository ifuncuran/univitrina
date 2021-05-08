import { useState, useCallback } from 'react';

export default (initialState) => {
  const [state, setState] = useState(initialState);
  const toggleState = useCallback(() => {
    setState((currentState) => !currentState);
  }, []);
  const setStateForce = useCallback((newState) => {
    setState(Boolean(newState));
  }, []);
  return [state, toggleState, setStateForce];
};
