import React from 'react';
import { Container } from '@material-ui/core';
import PropTypes from 'prop-types';
import useStyles from './style';

function MainContainer(props) {
  const classes = useStyles();
  const { fixed, children } = props;

  return (
    <Container className={classes.container} fixed={fixed}>
      {children}
    </Container>
  );
}

// чтобы прокинуть props.children и props.fixed:
MainContainer.propTypes = {
  children: PropTypes.node.isRequired,
  fixed: PropTypes.bool,
};
MainContainer.defaultProps = {
  fixed: false,
};

export default MainContainer;
