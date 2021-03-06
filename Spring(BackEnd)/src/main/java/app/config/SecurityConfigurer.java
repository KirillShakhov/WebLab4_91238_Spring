package app.config;

import app.auth.TokenAuthenticationEntryPoint;
import app.auth.TokenFilter;
import app.auth.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Конфигурация проверки токенов
public class SecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;
    public SecurityConfigurer(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter customFilter = new TokenFilter(tokenProvider);
        http.exceptionHandling()
                //Вызов точки входа
                .authenticationEntryPoint(new TokenAuthenticationEntryPoint())
                .and()
                //Вызов фильтра TokenFilter для проверки токена
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
