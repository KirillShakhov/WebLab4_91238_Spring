package app.services;

import app.repositories.UserRepository;
import app.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Сервис по управлению человеками
//Человек - в данном случае это информация(Имя, Токен) для того, чтобы мы могли хранить токен для username.
@Service
public class UserService implements UserDetailsService {

	//Поиск человека по имени
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return find(username);
	}

	//Предоставляет доступ для управлением человеками
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	//Добавление и сохранение человека
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	//Поиск по имени
	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	//Обнуление токена
	public void invalidateToken(String username) {
		User user = userRepository.findOneByUsername(username);
		user.setAuthToken(null);
		userRepository.save(user);
	}

	//Поиск по токену
	public User findByAuthToken(String token) {
		return userRepository.findByAuthTokenEquals(token);
	}

}
