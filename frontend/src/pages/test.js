import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Typography, Container, Grid, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from '@mui/material';
import { useAuth } from '../../contexts/AuthContext';
import axios from 'axios'; // Importe o axios

const DashboardContainer = styled(Container)(() => ({
  padding: '10px'
}));

const LogoContainer = styled(Paper)(() => ({
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  padding: '10px',
  marginBottom: '10px',
}));

const Logo = styled('img')(() => ({
  maxWidth: '200px',
}));

{codeCashier && codeCashier.length > 0 ? "CAIXA ABERTO" : "CAIXA FECHADO"}

const CardsContainer = styled('div')(() => ({
  marginBottom: '10px',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'row',
  gap: '10px',
  fontFamily: 'Roboto',
  textAlign: 'center',
}));

const Card = styled(Paper)(() => ({
}));

const ProductListContainer = styled(Paper)(() => ({
  maxHeight: '300px',
  overflowY: 'auto',
  marginBottom: '20px',
  height: '200px'
}));

const SubtotalContainer = styled(Paper)(() => ({
}));

const generateOrderCode = () => {
  return Math.random().toString(36).substring(2, 12).toUpperCase();
};

export const Dashboard = () => {
  const [cartItems, setCartItems] = useState([]);
  const [totalReceived, setTotalReceived] = useState('');
  const { user, logout } = useAuth();
  const [orderCode] = useState(generateOrderCode()); // Definindo o código da compra como estado inicial

  useEffect(() => {
    const handleKeyDown = async (event) => {
      const key = event.key;
      switch (key) {
        case 'F7':
          console.log('Valor Recebido');
          handleTotalReceivedChange();
          break;
        case 'F8':
          console.log('Confirmar Venda');
          sendOrderToBackend();
          break;
        case 'F9':
          console.log('Adicionar Produto');
          showAddProductPrompt();
          break;
        case 'F10':
          console.log('Remover Produto');
          break;
        case 'F11':
          console.log('Consultar Preço');
          break;
        case 'F12':
          confirmAndClose();
          break;
        default:
          break;
      }
    };

    document.addEventListener('keydown', handleKeyDown);

    return () => {
      document.removeEventListener('keydown', handleKeyDown);
    };
  }, []);

  const showAddProductPrompt = () => {
    const barcode = prompt('Digite o código de barras do produto:');
    if (barcode) {
      addProductByBarcode(barcode);
    }
  };

  useEffect(() => {
    const updateCartItems = async () => {
      const updatedCartItems = [];
  
      // Crie um mapa para armazenar produtos com suas quantidades
      const productMap = new Map();
  
      // Percorra os itens do carrinho
      cartItems.forEach(item => {
        // Verifique se o produto já está no mapa
        if (productMap.has(item.code)) {
          // Se sim, atualize a quantidade
          const existingItem = productMap.get(item.code);
          existingItem.quantity += item.quantity;
          // Atualize o mapa
          productMap.set(item.code, existingItem);
        } else {
          // Se não, adicione o produto ao mapa
          productMap.set(item.code, { ...item });
        }
      });
  
      // Converta o mapa de volta para um array de itens
      productMap.forEach(product => updatedCartItems.push(product));
  
      // Atualize o estado do carrinho com os itens atualizados
      setCartItems(updatedCartItems);
    };
  
    updateCartItems();
  }, [cartItems]);
  
    
  const addProductByBarcode = async (barcode) => {
    try {
      // Faça uma requisição para o endpoint que retorna os detalhes do produto com base no código de barras
      const response = await axios.get(`http://localhost:8080/products/${barcode}`);
      const product = response.data;
      setCartItems(prevCartItems => [{ ...product, quantity: 1 }, ...prevCartItems]);
    } catch (error) {
      console.error('Erro ao adicionar produto:', error);
    }
  };
  
  const sendOrderToBackend = async () => {
    try {
        const confirmed = window.confirm('Tem certeza que deseja finalizar a venda?');
        if (!confirmed) return;

        // Supondo que 'registration' contenha a matrícula do funcionário
        const registration = user.registration;

        const orderDTO = {
            code: orderCode,
            items: cartItems.map(item => ({
                productCode: item.code,
                quantity: item.quantity
            }))
        };

        console.log(orderDTO)

        const response = await axios.post(`http://localhost:8080/orders/${registration}`, orderDTO);
        console.log('VENDA FINALIZADA COM SUCESSO!')
        console.log(response.data);
        // window.location.reload()
    } catch (error) {
        console.error('Erro ao enviar pedido:', error);
    }
};


  const confirmAndClose = () => {
    if (window.confirm('Tem certeza que deseja fechar o caixa?')) {
      console.log('Fechar Caixa');
      logout();
      // Aqui você pode adicionar a lógica para fechar o caixa
    }
  };

  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + (item.price * item.quantity), 0).toFixed(2);
  };

  const handleTotalReceivedChange = () => {
    const received = prompt('Digite o valor recebido pelo cliente:');
    if (received) {
      setTotalReceived(received);
    }
  };

  const calculateChange = () => {
    const total = parseFloat(calculateTotal());
    const received = parseFloat(totalReceived);
    if (!isNaN(total) && !isNaN(received)) {
      return (received - total).toFixed(2);
    }
    return '';
  };

  return (
    <Container maxWidth="lg">
      <DashboardContainer maxWidth="xl">
        <Typography variant="h2" align="center" fontWeight={700} gutterBottom>
          CAIXA ABERTO
        </Typography>
        <Typography variant="h6" align="center" gutterBottom>
          Operador: {user.name} - Matrícula: {user.registration} - Hora: {new Date().toLocaleTimeString()}
        </Typography>

        <Grid container spacing={2}>
          <Grid item xs={12} md={4}>
            <LogoContainer elevation={3}>
              <Logo src="https://i.pinimg.com/736x/3a/cd/bb/3acdbb1f38c429fa37304bcc825a158a.jpg" alt="Logo do mercado" />
            </LogoContainer>
          </Grid>

          <Grid item xs={12} md={8}>
            <ProductListContainer elevation={3}>
              <TableContainer>
                <Table>
                  <TableHead>
                    <TableRow>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>ITEM</TableCell>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>CÓDIGO</TableCell>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>DESCRIÇÃO</TableCell>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>QTD</TableCell>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>VALOR UNT</TableCell>
                      <TableCell variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px' }}>TOTAL</TableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {cartItems.map((item, index) => (
                      <TableRow key={index}>
                        <TableCell>{index + 1}</TableCell>
                        <TableCell>{item.code}</TableCell>
                        <TableCell>{item.name}</TableCell>
                        <TableCell>{item.quantity}</TableCell>
                        <TableCell>R$ {item.price.toFixed(2)}</TableCell>
                        <TableCell>R$ {(item.quantity * item.price).toFixed(2)}</TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </ProductListContainer>

            <CardsContainer container spacing={2} justifyContent="center">
              
              <Grid item xs={12}>
                <Card elevation={3}>
                  <Typography variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}}>
                    CÓDIGO
                  </Typography>
                  <h5>{orderCode}</h5>
                </Card>
              </Grid>

              <Grid item xs={12}>
                <Card elevation={3}>
                  <Typography variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}}>
                    VALOR DO ITEM
                  </Typography>
                  <h5>R$ {cartItems.length > 0 ? cartItems[0].price.toFixed(2) : '0,00'}</h5>
                </Card>
              </Grid>

              <Grid item xs={12}>
                <Card elevation={3}>
                  <Typography variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}}>
                    TOTAL DO ITEM
                  </Typography>
                  <h5>R$ {cartItems.length > 0 ? (cartItems[0].quantity * cartItems[0].price).toFixed(2) : '0,00'}</h5>
                </Card>
              </Grid>
            </CardsContainer>
          </Grid>
        </Grid>

        <Grid container spacing={2}>
          <Grid item xs={12} md={6}>
            <Typography variant="h6" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '5px', fontSize: '16px'}}>
              Comandos do Sistema
            </Typography>
            <Paper elevation={3} style={{ padding: '20px', marginBottom: '20px' }}>
              <Typography variant="body1">F7. Receber Pagamento</Typography>
              <Typography variant="body1">F8. Finalizar Venda</Typography>
              <Typography variant="body1">F9. Adicionar Produto</Typography>
              {/* <Typography variant="body1">F10. Remover Produto</Typography> */}
              {/* <Typography variant="body1">F11. Consultar Preço</Typography> */}
              <Typography variant="body1">F12. Fechar Caixa</Typography>
            </Paper>
          </Grid>

          <Grid item xs={12} md={6}>
            <SubtotalContainer elevation={3}>
              <TableContainer>
                <Table>
                  <TableBody>
                    <TableRow>
                      <TableCell variant="head" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}} fontWeight={700} colSpan={3}><h1>Subtotal</h1></TableCell>
                      <TableCell variant="body" align="center" colSpan={3} sx={{ fontSize: '40px', fontWeight: '900' }}>R$ {calculateTotal()}</TableCell>
                    </TableRow>
                    {totalReceived && (
                      <>
                        <TableRow>
                          <TableCell variant="head" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}} colSpan={3}>Total Recebido</TableCell>
                          <TableCell variant="body" align="center" colSpan={3} sx={{ fontWeight: '900' }}>R$ {parseFloat(totalReceived).toFixed(2)}</TableCell>
                        </TableRow>
                        <TableRow>
                          <TableCell variant="head" align="center" sx={{ bgcolor: '#3f51b5', color: 'white', padding: '10px', fontSize: '16px'}} colSpan={3}>Troco</TableCell>
                          <TableCell variant="body" align="center" colSpan={3} sx={{ fontWeight: '900' }}>{calculateChange()}</TableCell>
                        </TableRow>
                      </>
                    )}
                  </TableBody>
                </Table>
              </TableContainer>
            </SubtotalContainer>
            {!totalReceived && (
              <TextField
                label="Valor Recebido"
                variant="outlined"
                type="number"
                value={totalReceived}
                fullWidth
                sx={{ marginTop: '10px' }}
                onChange={(e) => setTotalReceived(e.target.value)}
                disabled={true}
              />
            )}
          </Grid>
        </Grid>
      </DashboardContainer>
    </Container>
  );
};
