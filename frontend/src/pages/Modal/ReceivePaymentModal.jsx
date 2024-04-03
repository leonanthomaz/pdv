import React, { useState } from 'react';
import { Modal, Button, Typography, TextField, Grid } from '@mui/material';

export const ReceivePaymentModal = ({ open, handleClose, handleReceivePayment }) => {
  const [paymentAmount, setPaymentAmount] = useState('');

  const handlePaymentAmountChange = (event) => {
    setPaymentAmount(event.target.value);
  };

  const handleConfirm = () => {
    handleReceivePayment(paymentAmount);
    setPaymentAmount('');
    handleClose();
  };

  return (
    <Modal open={open} onClose={handleClose}>
      <Grid container direction="column" justifyContent="center" alignItems="center" style={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', backgroundColor: 'white', padding: '20px', width: '300px' }}>
        <Typography variant="h5" gutterBottom>Receber Pagamento</Typography>
        <TextField
          value={paymentAmount}
          onChange={handlePaymentAmountChange}
          variant="outlined"
          label="Valor Recebido"
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
