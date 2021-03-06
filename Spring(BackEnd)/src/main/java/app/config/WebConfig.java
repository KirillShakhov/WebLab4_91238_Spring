package app.config;

import app.auth.TokenProvider;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//Тут настраивается кто и куда может попасть из вне.
//Можно даже добавить проверку на то, с какого адреса ты заходишь сюда
@Configurable
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

	private final TokenProvider tokenProvider;

	public WebConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Тут конфиг, по каким адресам может обращаться клиент
		//GET /api/points/ - Получение точек
		//POST /api/points - Отправка точек
		//POST /api/users/logout - Отправка команды на деавторизацию
		http
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/points/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/points/**").authenticated()
				.antMatchers(HttpMethod.POST, "/api/users/logout").authenticated()
				.anyRequest().permitAll()
				.and()
				.apply(new SecurityConfigurer(tokenProvider));
	}


}
