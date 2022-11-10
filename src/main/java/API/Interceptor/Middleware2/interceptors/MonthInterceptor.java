package API.Interceptor.Middleware2.interceptors;

import API.Interceptor.Middleware2.entities.Month;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "März"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni")));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthIdString = request.getHeader("monthNumber");
        if (monthIdString == null || monthIdString == "") {
            response.setStatus(400);
            request.setAttribute("month", new Month(0, "nope", "nope", "nope"));
            return true;
        }
        int montId = Integer.parseInt(monthIdString);
        Optional<Month> month = months.stream().filter(singleMont -> {
            return singleMont.getMonthNumber() == montId;
        }).findFirst();
        if (month.isPresent()) request.setAttribute("month", month.get());
        else request.setAttribute("month", new Month(0, "nope", "nope", "nope"));
        response.setStatus(200);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}