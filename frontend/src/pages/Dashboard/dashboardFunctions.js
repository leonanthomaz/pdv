import axios from 'axios';

// Função para criar um novo caixa ou recuperar do localStorage
export const createCashier = async (setCodeCashier, setCartItems, setTotal) => {
  try {
    // Verifica se há um caixa no localStorage
    const localStorageCashier = localStorage.getItem('codeCashier');
    console.log("CODIGO ENCONTRADO: " + localStorageCashier)

    if (localStorageCashier && localStorageCashier !== null) {
      // Traz o caixa existente
      const response = await axios.get(`http://localhost:8080/cashier/find?code=${localStorageCashier}`);
      console.log("ANALISANDO NO BANCO : " + response.data)   

      if (response.data && response.data.items && response.data.items.length > 0) {
        setCodeCashier(localStorageCashier);
        setCartItems(response.data.items);
        setTotal(response.data.total);
        console.log("ITENS : " + response.data.items)   
        console.log("TOTAL : " + response.data.total)   
      } else {
        console.error('A resposta do servidor não possui itens válidos:', response.data);
      }
      return;
    }

    // Se não houver caixa no localStorage, cria um novo
    const response = await axios.post('http://localhost:8080/cashier/create');
    const newCodeCashier = response.data;
    setCodeCashier(newCodeCashier);

    // Armazena o novo caixa no localStorage
    localStorage.setItem('codeCashier', newCodeCashier);
  } catch (error) {
    console.error('Erro ao criar o caixa:', error);
  }
};

export const addItemToCart = async (inputValue, codeCashier, setCartItems, setTotal) => {
  try {  
    if (inputValue) {
      const response = await axios.post(`http://localhost:8080/cashier/add-item`, { code: codeCashier, codeBar: inputValue });
      console.log('Produto adicionado ao carrinho com sucesso:', response.data);
      if (response.data && response.data.items && response.data.items.length > 0) {
          setCartItems(response.data.items); // Atualiza o estado do carrinho com os itens retornados pelo backend
          setTotal(response.data.total); // Atualiza o estado do subtotal com o valor retornado pelo backend
      } else {
          console.error('A resposta do servidor não possui itens válidos:', response.data);
      }
    }      
  } catch (error) {
      console.error('Erro ao adicionar produto:', error);
  }
};

export const finalizePurchase = async (codeCashier) => {
  try {
    const response = await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "WAITING_PAYMENT" });
    const WAITING_PAYMENT = response.data.status;
    console.log("Status do caixa atualizado com sucesso para AGUARDANDO PAGAMENTO:", WAITING_PAYMENT);
    return true;
  } catch (error) {
    console.error('Erro ao fechar a venda:', error);
    return false; // Retorna falso para indicar que houve um erro no processamento do pagamento
  }
};

export const receivedPayment = async (inputValue, codeCashier, setChangeValue, setTotalReceived, setValueEntered) => {
  try {
    
    //se nao houver erro, prossiga para o calculo de rebimento de valores - total = troco
    if (!isNaN(inputValue)) {
      const response = await axios.put(`http://localhost:8080/cashier/change`, { codeCashier: codeCashier, receivedAmount: parseFloat(inputValue) });
      const change = response.data;
      console.log('Troco:', change);

      setChangeValue(change);//atualiza o troco
      setTotalReceived(parseFloat(inputValue)); // Define o valor recebido
      setValueEntered(true); // Define como verdadeiro para esconder o campo de valor recebido

      //se tudo ocorrer como o esperado, atualiza o status do pagamento para - PAGO
      const responseStatus = await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "PAID" });
      const PAID = responseStatus.data.status;
      console.log("Status do caixa atualizado com sucesso para PAGO:", PAID);

      return true; // Retorna verdadeiro para indicar que o pagamento foi processado com sucesso

    } else {
      console.error('Valor recebido inválido.');
      return false; // Retorna falso para indicar que houve um erro no processamento do pagamento
    }

  } catch (error) {
    console.error('Erro ao processar pagamento:', error);
    return false; // Retorna falso para indicar que houve um erro no processamento do pagamento
  }
}

export const analyseOfCashier = async (codeCashier, finishSale) => {
  try {

    if (!codeCashier) {
      console.log("Não há código de caixa presente.");
      return;
    }
    
    const response = await axios.get(`http://localhost:8080/cashier/find?code=${codeCashier}`);
    const cashier = response.data;

    if(cashier && cashier.status === "FINISHED"){
      localStorage.removeItem('codeCashier');
      finishSale()
      return;
    }
    
    // Analisa o status e atualiza se necessário
    if (cashier && cashier.status === "PAID") {
      const responseStatus = await axios.put(`http://localhost:8080/cashier/update-status`, { codeCashier: codeCashier, status: "FINISHED" });
      const status = responseStatus.data.status;
      console.log("Status do caixa atualizado com sucesso - CAIXA FINALIZADO :", status);   
      localStorage.removeItem('codeCashier');
      finishSale()
      return;
    }

    console.log("MONITORANDO PAGAMENTO...");

  } catch (error) {
    console.error("Erro ao monitorar pagamento:", error);
  }
};

export const exitCashier = (logout) => {
  logout();
};

// export const startNewTransaction = async () => {
//   try {
//     // Limpa o código do caixa no LocalStorage para iniciar uma nova transação
//     localStorage.removeItem('codeCashier');
//     console.log('Nova transação iniciada. O código do caixa foi removido do LocalStorage.');
//   } catch (error) {
//     console.error('Erro ao iniciar nova transação:', error);
//     throw error;
//   }
// };


