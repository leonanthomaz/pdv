import styled from 'styled-components';
import { Container, Paper } from '@mui/material';

export const DashboardContainer = styled(Container)(() => ({
  padding: '5px'
}));

export const LogoContainer = styled(Paper)(() => ({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  padding: '10px',
  marginBottom: '5px',
}));

export const TextFieldInput = styled('input')`
margin-top: 10px;
`;

export const Logo = styled('img')(() => ({
  maxWidth: '200px',
}));

export const CardsContainer = styled('div')(() => ({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'row',
  gap: '5px',
  fontFamily: 'Roboto',
  textAlign: 'center',
}));

export const Card = styled(Paper)(() => ({
}));

export const ProductListContainer = styled(Paper)(() => ({
  maxHeight: '300px',
  overflowY: 'auto',
  marginBottom: '10px',
  height: '200px'
}));

export const SubtotalContainer = styled(Paper)(() => ({
}));
