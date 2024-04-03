package app.vercel.leonanthomaz.pdv.service;

import app.vercel.leonanthomaz.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por lidar com operações relacionadas a usuários.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Carrega os detalhes do usuário pelo nome de usuário (registro).
     *
     * @param registration O registro do usuário a ser carregado.
     * @return Objeto UserDetails contendo os detalhes do usuário.
     * @throws UsernameNotFoundException Se o usuário com o registro fornecido não for encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String registration) throws UsernameNotFoundException {
        return userRepository.findByRegistration(registration);
    }
}
