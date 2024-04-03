import React, { useState, useEffect } from 'react';
import { Typography, Container, Grid, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { useAuth } from '../../contexts/AuthContext';
import * as D from './DashboardStyles'; // Importando estilos
import * as DashboardFunctions from './dashboardFunctions';
import { ModalConfirmation } from '../Modal/ModalConfirmation';
import { ModalInput } from '../Modal/ModalInput';
import { ModalError } from '../Modal/ModalError'; // Importe o componente ModalError

export const Dashboard = () => {
  //Código da compra
  const [codeCashier, setCodeCashier] = useState([]);
  const [cartItems, setCartItems] = useState([]);
  //Total da compra
  const [total, setTotal] = useState(0);
  //Total da compra
  const [totalForItems, setTotalForItems] = useState(0);
  //Função de tempo
  const [currentTime, setCurrentTime] = useState(new Date().toLocaleString('pt-BR', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' }));
  //Valor recebido
  const [totalReceived, setTotalReceived] = useState('');
  //Troco
  const [changeValue, setChangeValue] = useState('');
  //Ativação do input de recebimento do valor 
  const [valueEntered, setValueEntered] = useState(false);

  //Usuário
  const { user, logout } = useAuth();

  //Modais
  const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
  
  const [isInputModalOpen, setIsInputModalOpen] = useState(false);
  const [isErrorModalOpen, setIsErrorModalOpen] = useState(false);
  
  const [modalTitle, setModalTitle] = useState('');
  const [errorModalMessage, setErrorModalMessage] = useState('');
  
  const [modalContent, setModalContent] = useState('');
  const [errorModalTitle, setErrorModalTitle] = useState('');

  const [isPaymentModalOpen, setIsPaymentModalOpen] = useState(false);

  const handleOpenConfirmationModal = (title, content) => {
    setModalTitle(title);
    setModalContent(content);
    setIsConfirmationModalOpen(true);
  };

  const handleCloseConfirmationModal = () => {
    setIsConfirmationModalOpen(false);
    setModalTitle('');
    setModalContent('');
  };

  const handleOpenInputModal = (title) => {
    setModalTitle(title);
    setIsInputModalOpen(true);
    setIsPaymentModalOpen(true); // Altera o estado para abrir o modal de receber pagamento
  };

  const handleCloseInputModal = () => {
    setIsInputModalOpen(false);
    setModalTitle('');
    setIsPaymentModalOpen(false); // Altera o estado para abrir o modal de receber pagamento
  };

  const handleOpenErrorModal = (title, message) => {
    setErrorModalTitle(title);
    setErrorModalMessage(message);
    setIsErrorModalOpen(true);
  };
  
  const handleCloseErrorModal = () => {
    setIsErrorModalOpen(false);
  };

  //TEMPO
  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentTime(new Date().toLocaleString('pt-BR', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' }));
    }, 1000); // Atualiza a cada segundo

    return () => clearInterval(interval);
  }, []);

  //COMANDOS DO TECLADO
  useEffect(() => {
    const handleKeyDown = async (event) => {
      const key = event.key;

      if (event.target.tagName !== "INPUT") {
        event.preventDefault();
      }

      switch (key) {
        case 'F9':
          handleOpenConfirmationModal('Abrir Caixa', 'Deseja abrir o caixa?');
          break;
        case 'F10':     
          if (codeCashier.length === 0) return handleOpenErrorModal('Erro', 'Por favor, abra o caixa antes de realizar outras operações.');
          handleOpenInputModal('Adicionar Produto');
          break;
        case 'F11':
          if (codeCashier.length === 0) return handleOpenErrorModal('Erro', 'Por favor, abra o caixa antes de realizar outras operações.');
          handleOpenInputModal('Finalizar Venda');            
          break;
        case 'F12':
          if (codeCashier.length === 0) return handleOpenErrorModal('Erro', 'Por favor, abra o caixa antes de realizar outras operações.');
          handleOpenConfirmationModal('Fechar Caixa', 'Tem certeza que deseja fechar o caixa?');
          break;
        default:
          break;
      }
    };

    document.addEventListener('keydown', handleKeyDown);

    return () => {
      document.removeEventListener('keydown', handleKeyDown);
    };
  }, [codeCashier.length]);

  //ABRIR MODAL CONFIRMAÇÃO
  const handleConfirm = async () => {
    switch (modalTitle) {
      case 'Abrir Caixa':
        DashboardFunctions.createCashier(setCodeCashier);
        break;
      case 'Fechar Caixa':
        DashboardFunctions.confirmAndClose(logout);
        break;
      default:
        break;
    }
    handleCloseConfirmationModal();
  };

  //ABRIR MODAL INPUT
  const handleInput = async (inputValue) => {
    switch (modalTitle) {
      case 'Adicionar Produto':
        DashboardFunctions.addItemToCart(inputValue, codeCashier, setCartItems, setTotal, setTotalForItems);
        break;
      case 'Finalizar Venda':
        DashboardFunctions.finalizePurchase(inputValue, codeCashier, setChangeValue, setTotalReceived, setValueEntered, finishSale);
        break;
      default:
        break;
    }
  };

  const finishSale = () => {
    setCodeCashier([]);
    setCartItems([]);
    setTotal(0);
    setTotalReceived('');
    setChangeValue('');
    setValueEntered(false);
  };
  
  return (
    <Container maxWidth="lg">
      <D.DashboardContainer>
        <Typography variant="h4" align="center" gutterBottom style={{ fontSize: '50px', color: codeCashier.length > 0 ? '#ff8c00' : '#000000' }}>
          <span style={{ color: codeCashier.length > 0 ? '#ff8c00' : '#000000', fontWeight: 700 }}>{codeCashier.length > 0 ? "CAIXA ABERTO" : "CAIXA FECHADO"}</span>
        </Typography>
        <Typography variant="subtitle1" align="center" gutterBottom style={{ color: '#ff8c00' }}>
          Operador: {user.name} - Matrícula: {user.registration}
        </Typography>
        <Typography variant="subtitle1" align="center" gutterBottom style={{ color: '#ff8c00' }}>
          {currentTime}
        </Typography>

        <Grid container spacing={3} style={{ marginTop: '20px' }}>
          <Grid item xs={12} md={4}>
            <D.LogoContainer elevation={3}>
              <D.Logo src="https://yakissobadelivery.wabiz.delivery/stores/yakissobadelivery/img/homeLogo.png?20240306191100" alt="Logo do mercado" />
            </D.LogoContainer>
          </Grid>

          <Grid item xs={12} md={8}>
            <D.ProductListContainer elevation={3}>
              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>ITEM</TableCell>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>CÓDIGO</TableCell>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>DESCRIÇÃO</TableCell>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>QTD</TableCell>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>VALOR UNT</TableCell>
                      <TableCell variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px' }}>TOTAL</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {cartItems && cartItems
                      .sort((a, b) => b.totalPrice - a.totalPrice) // Ordena os itens pelo preço total em ordem decrescente
                      .map((item, index) => (
                        <TableRow key={index}>
                          <TableCell>{index + 1}</TableCell>
                          <TableCell>{item.product.codeBar}</TableCell>
                          <TableCell>{item.product.name}</TableCell>
                          <TableCell>{item.quantity}</TableCell>
                          <TableCell>R$ {String(item.price.toFixed(2)).replace('.', ',')}</TableCell>
                          <TableCell>R$ {String(item.totalPrice.toFixed(2)).replace('.', ',')}</TableCell>
                        </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </D.ProductListContainer>

            <D.CardsContainer container spacing={2} justifyContent="center">

              <Grid item xs={12}>
                <D.Card elevation={3}>
                  <Typography variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }}>
                    CÓDIGO
                  </Typography>
                  <Typography variant="subtitle1" align="center" style={{ fontWeight: 'bold' }}>{codeCashier.length > 0 ? codeCashier : 'Sem código' }</Typography>
                </D.Card>
              </Grid>

              <Grid item xs={12}>
                <D.Card elevation={3}>
                  <Typography variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }}>
                    PREÇO
                  </Typography>
                  <Typography variant="subtitle1" align="center">R$ {cartItems.length > 0 ? String(cartItems[0].price.toFixed(2)).replace('.', ',') : '0,00'}</Typography>
                </D.Card>
              </Grid>

              <Grid item xs={12}>
                <D.Card elevation={3}>
                  <Typography variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }}>
                    TOTAL
                  </Typography>
                  <Typography variant="subtitle1" align="center">R$ {cartItems.length > 0 ? String(cartItems[0].totalPrice.toFixed(2)).replace('.', ',') : '0,00'}</Typography>
                </D.Card>
              </Grid>

            </D.CardsContainer>
          </Grid>
          <ModalConfirmation
            open={isConfirmationModalOpen}
            handleClose={handleCloseConfirmationModal}
            title={modalTitle}
            content={modalContent}
            handleConfirm={handleConfirm}
          />
          <ModalInput
            open={isInputModalOpen}
            handleClose={handleCloseInputModal}
            title={modalTitle}
            handleInput={handleInput}
          />
          <ModalError
            open={isErrorModalOpen}
            onClose={handleCloseErrorModal}
            title={errorModalTitle}
            message={errorModalMessage}
          />
          {isPaymentModalOpen && (
            <ModalInput
              open={isPaymentModalOpen}
              handleClose={handleCloseInputModal}
              title={modalTitle}
              handleInput={handleInput}
            />
          )}
        </Grid>

        <Grid container spacing={3} style={{ marginTop: '10px' }}>
          <Grid item xs={12} md={6}>
            <Typography variant="h6" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }}>
              Comandos do Sistema
            </Typography>
            <Paper elevation={3} style={{ padding: '10px', marginBottom: '20px'}}>
              <Typography style={{ fontSize: '14px', fontWeight: 'bold' }} variant="body1">F9. Abrir Caixa</Typography>
              <Typography style={{ fontSize: '14px', fontWeight: 'bold' }} variant="body1">F10. Adicionar Produto</Typography>
              <Typography style={{ fontSize: '14px', fontWeight: 'bold' }} variant="body1">F11. Finalizar compra</Typography>
              <Typography style={{ fontSize: '14px', fontWeight: 'bold' }} variant="body1">F12. Fechar Caixa</Typography>
            </Paper>
          </Grid>

          <Grid item xs={12} md={6}>
            <D.SubtotalContainer elevation={3}>
              <TableContainer>
                <Table>
                  <TableBody>
                    <TableRow>
                      <TableCell variant="head" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }} colSpan={3}><h1>Subtotal</h1></TableCell>
                      <TableCell variant="body" align="center" colSpan={3} style={{ fontSize: '40px', fontWeight: 'bold' }}>R$ {total ? String(total.toFixed(2)).replace('.', ',') : '0,00'}</TableCell>
                    </TableRow>
                    {valueEntered && (
                      <>
                        <TableRow>
                          <TableCell variant="head" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }} colSpan={3}>Total Recebido</TableCell>
                          <TableCell variant="body" align="center" colSpan={3} style={{ fontWeight: 'bold' }}>R$ {totalReceived ? String(totalReceived.toFixed(2)).replace('.', ',') : '0,00'}</TableCell>
                        </TableRow>
                        <TableRow>
                          <TableCell variant="head" align="center" style={{ backgroundColor: '#ff8c00', color: 'white', padding: '5px', fontSize: '16px', fontWeight: 'bold' }} colSpan={3}>Troco</TableCell>
                          <TableCell variant="body" align="center" colSpan={3} style={{ fontWeight: 'bold' }}>
                            {/* Mostrando o troco */}
                            { changeValue ? 'R$' + String(parseFloat(changeValue).toFixed(2)).replace('.', ',') : '' }
                          </TableCell>
                        </TableRow>
                      </>
                    )}
                  </TableBody>
                </Table>
              </TableContainer>
            </D.SubtotalContainer>
          </Grid>
        </Grid>
      </D.DashboardContainer>
    </Container>
  );
};
