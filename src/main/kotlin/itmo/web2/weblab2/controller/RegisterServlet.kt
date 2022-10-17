package itmo.web2.weblab2.controller

import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
@WebServlet(name = RegisterServlet.nameOfServlet)
class RegisterServlet : HttpServlet() {
    companion object{
        const val nameOfServlet = "registerServlet"
    }
    override fun service(req: ServletRequest?, res: ServletResponse?) {
        super.service(req, res)
    }
}