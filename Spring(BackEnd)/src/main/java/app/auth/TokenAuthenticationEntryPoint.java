//Spring Security
package app.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Точка входа
//В случаи ошибок авторизации логирует сообщения
//Токен - это специальный код, разрешающий доступ к данным конкретного пользователя.
//Slf4j - Логгер, записывает все в консоль
@Slf4j
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.debug("Token authentication failed:" + authException);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED	, "Token authentication failed");
    }

}
