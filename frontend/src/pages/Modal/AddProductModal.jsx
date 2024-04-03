import React, { useState } from 'react';
import { Modal, Button, Typography, TextField, Grid } from '@mui/material';

export const AddProductModal = ({ open, handleClose, handleAddProduct }) => {
  const [productCode, setProductCode] = useState('');

  // No componente AddProductModal
  const handleProductCodeChange = (event) => {
    setProductCode(event.target.value); // Certifique-se de que productCode está sendo atualizado corretamente
  };

  const handleConfirm = () => {
    handleAddProduct(productCode); // Certifique-se de que handleAddProduct está sendo chamado corretamente
    setProductCode(''); // Limpa o estado após a confirmação
    handleClose();
  };

  return (
    <Modal open={open} onClose={handleClose}>
      <Grid container direction="column" justifyContent="center" alignItems="center" style={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', backgroundColor: 'white', padding: '20px', width: '300px' }}>
        <Typography variant="h5" gutterBottom>Adicionar Produto</Typography>
        {/* Correção aqui: value e onChange */}
        <TextField
          value={productCode}
          onChange={handleProductCodeChange}
          variant="outlined"
          label="Código do Produto"
          fullWidth
          style={{ marginBottom: '10px' }}
        />
        <Grid container spacing={1}>
          <Grid item xs={6}>
            <Button variant="contained" onClick={handleConfirm} fullWidth>Confirmar</Button>
          </Grid>
          <Grid item xs={6}>
            <Button variant="contained" onClick={handleClose} color="error" fullWidth>Cancelar</Button>
          </Grid>
        </Grid>
      </Grid>
    </Modal>
  );
};
