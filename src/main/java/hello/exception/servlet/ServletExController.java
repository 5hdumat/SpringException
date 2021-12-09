package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Exception이 터지게되면 기본적으로 서블릿 컨테이너는 500에러를 발생시킨다.
 * 개발자가 직접 오류를 정의하고싶다면 sendError(error code, message); 를 사용하자.
 *
 * 참고로 서블릿은 Exception(예외)가 발생해서 서블릿 밖으로 전달되거나
 * 또는 'response.sendError()'가 호출 되었을 때 설정된 오류 페이지를 찾는다.
 */
@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외 발생");
    }

    /**
     * response.sendError() 를 호출하면 response 내부에는 오류가 발생했다는 상태를 저장해둔다.
     * 그리고 서블릿 컨테이너는 고객에게 응답 전에 response에 sendError() 가 호출되었는지 까본다.
     *
     * 그리고 호출되었다면 설정한 오류 코드에 맞추어 기본 오류 페이지를 보여준다.
     * 실행해보면 서블릿 컨테이너가 기본으로 제공하는 오류 화면을 볼 수 있다.
     */
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류");
    }

    @GetMapping("/error-400")
    public void error400(HttpServletResponse response) throws IOException {
        response.sendError(400, "400 오류");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
}
