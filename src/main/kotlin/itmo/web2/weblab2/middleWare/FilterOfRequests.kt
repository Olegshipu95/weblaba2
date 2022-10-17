package itmo.web2.weblab2.middleWare

import itmo.web2.weblab2.controller.ControllerServlet
import jakarta.servlet.FilterChain
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebFilter("/*")
class FilterOfRequests:HttpFilter() {
    override fun init() {
        super.init()
    }

    override fun doFilter(req: HttpServletRequest?, res: HttpServletResponse?, chain: FilterChain?) {
        servletContext.getNamedDispatcher(ControllerServlet.name).forward(req,res);
        // создания авторизации юзера
    }
}