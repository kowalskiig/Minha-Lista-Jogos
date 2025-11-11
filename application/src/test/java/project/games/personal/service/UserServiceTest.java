package project.games.personal.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.games.personal.dto.InsertUserDTO;
import project.games.personal.dto.UserDTO;
import project.games.personal.entities.User;
import project.games.personal.exception.ConflictException;
import project.games.personal.exception.ResourceNotFoundException;
import project.games.personal.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @DisplayName("Quando acessar método create User")
    @Nested
    class findById {

        @DisplayName("Deve executar usuario criado com sucesso")
        @Nested
        class Sucesso {

            @DisplayName("Quando receber dados completos não divergences")
            @Test
            void test1() {
                // dado

                InsertUserDTO insertUserDTO = new InsertUserDTO("test@gmail.com", "UserTest", "password");
                User user = User.builder()
                        .id(1L)
                        .email("teste@gmail.com")
                        .password("$2a$10$.mmz3OqUecF234Bic.FuYO5uZF9eZZGYM7aDkVLpqGVKUqBfhwrAC")
                        .name("Teste")
                        .build();

                Mockito.when(userRepository.findByEmail(any()))
                        .thenReturn(Optional.empty());

                Mockito.when(userRepository.save(any()))
                        .thenReturn(user);

                Mockito.when(passwordEncoder.encode(any()))
                        .thenReturn("$2a$10$.mmz3OqUecF234Bic.FuYO5uZF9eZZGYM7aDkVLpqGVKUqBfhwrAC");

                // quando

                UserDTO userDTO = userService.createUser(insertUserDTO);

                // validação

                assertThat(userDTO.getId())
                        .isEqualTo(user.getId());

                assertThat(userDTO.getEmail())
                        .isEqualTo(user.getEmail());

                assertThat(userDTO.getName())
                        .isEqualTo(user.getName());


            }
        }
        @DisplayName("Deve executar com erro")
        @Nested
        class Falha {

            @DisplayName("Quando email já existir, retornar conflictexception")
            @Test
            void test2(){

                //dado

                User user = new User();

                InsertUserDTO insertUserDTO = new InsertUserDTO("test@gmail.com", "UserTest", "password");
                Mockito.when(userRepository.findByEmail(any()))
                        .thenReturn(Optional.of(user));


                //quando
                ConflictException exception = Assertions.assertThrows(ConflictException.class, () -> {
                    userService
                            .createUser(insertUserDTO);
                });

                //entao
                assertThat(exception)
                        .isNotNull();



            }

        }
    }


}
