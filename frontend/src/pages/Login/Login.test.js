import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import { Login } from '.';

// Mock do axios
jest.mock('axios', () => ({
  post: jest.fn(() => Promise.resolve({ data: { token: 'mockedToken', user: 'mockedUser' } })),
}));

describe('Login component', () => {
  test('renders login form', () => {
    const { getByLabelText, getByText } = render(<Login />);

    expect(getByLabelText('Matricula')).toBeInTheDocument();
    expect(getByLabelText('Password')).toBeInTheDocument();
    expect(getByText('Login')).toBeInTheDocument();
    expect(getByText('Not Already have an account?')).toBeInTheDocument();
  });

  test('submits login form with valid data', async () => {
    const { getByLabelText, getByText } = render(<Login />);

    fireEvent.change(getByLabelText('Matricula'), { target: { value: 'mockedRegistration' } });
    fireEvent.change(getByLabelText('Password'), { target: { value: 'mockedPassword' } });

    fireEvent.click(getByText('Login'));

    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith('token', 'mockedToken');
      // Você pode adicionar mais verificações aqui conforme necessário
    });
  });

  // Você pode adicionar mais testes conforme necessário
});
