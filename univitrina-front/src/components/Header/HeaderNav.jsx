import { Box, Button } from '@material-ui/core';
import React from 'react';
import { Link } from 'react-router-dom';
import useStyles from './style';

const headersData = [
  {
    label: 'Университеты',
    href: '/universities',
  },
  {
    label: 'Специальности',
    href: '/specializations',
  },
  {
    label: 'Профессии',
    href: '/professions',
  },
];

function Nav() {
  const classes = useStyles();
  const ListNav = headersData.map(({ label, href }) => (
    <Button
      className={classes.navButton}
      key={label}
      color="inherit"
      to={href}
      component={Link}
    >
      {label}
    </Button>
  ));

  return (
    <Box
      display={{
        xs: 'none',
        sm: 'none',
        md: 'block',
        lg: 'block',
      }}
      className={classes.nav}
    >
      {ListNav}
    </Box>
  );
}

export default Nav;
