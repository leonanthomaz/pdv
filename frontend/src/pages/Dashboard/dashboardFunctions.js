import axios from 'axios';

export const createCashier = async (setCodeCashier) => {
  try {
    const response = await axios.post('http://localhost:8080/cashier/create');
    setCodeCashier(response.data);
  } catch (error) {
    console.error('Erro ao criar o caixa:', error);
  }
};

export const addItemToCart = async (inputValue, codeCashier, setCartItems, setTotal, setTotalForItems) => {
  try {  
      if (inputValue) {
          const response = await axios.post(`http://localhost:8080/cashier/add-item`, { code: codeCashier, codeBar: inputValue });
          console.log('Produto adicionado ao carrinho com sucesso:', response.data);
          if (response.data && response.data.items && response.data.items.length > 0) {
              setCartItems(response.data.items); // Atualiza o estado do carrinho com os itens retornados pelo backend
              setTotal(response.data.total); // Atualiza o estado do subtotal com o valor retornado pelo backend
              
              // Calcula o total para cada item individualmente
              const totalForItems = response.data.items.map(item => item.totalPrice);
              setTotalForItems(totalForItems); // Atualiza o estado com os totais por item
          } else {
              console.error('A resposta do servidor não possui itens válidos:', response.data);
          }
      }      
  } catch (error) {
      console.error('Erro ao adicionar produto:', error);
  }
};

export const finalizePurchase = async (inputValue, codeCashier, setChangeValue, setTotalReceived, setValueEntered) => {
  try {

    const response = await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "WAITING_PAYMENT" });
    const WAITING_PAYMENT = response.data.status;
    console.log("Status do caixa atualizado com sucesso para AGUARDANDO PAGAMENTO:", WAITING_PAYMENT);
    
    if (!isNaN(inputValue)) {
      const response = await axios.put(`http://localhost:8080/cashier/change`, { codeCashier: codeCashier, receivedAmount: parseFloat(inputValue) });
      const change = response.data;
      console.log('Troco:', change);
      setChangeValue(change);
      setTotalReceived(parseFloat(inputValue)); // Define o valor recebido
      setValueEntered(true); // Define como verdadeiro para esconder o campo de valor recebido

      const responseStatus = await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "PAID" });
      const PAID = responseStatus.data.status;
      console.log("Status do caixa atualizado com sucesso para PAGO:", PAID);

      if (PAID === "PAID") {
        await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "FINISHED" });
        console.log("COMPRA FINALIZADA COM SUCESSO!");
      }
      return true; // Retorna verdadeiro para indicar que o pagamento foi processado com sucesso
    } else {
      console.error('Valor recebido inválido.');
      return false; // Retorna falso para indicar que houve um erro no processamento do pagamento
    }
  } catch (error) {
    console.error('Erro ao calcular troco:', error);
    return false; // Retorna falso para indicar que houve um erro no processamento do pagamento
  }
};

export const confirmAndClose = (logout) => {
  logout();
};
