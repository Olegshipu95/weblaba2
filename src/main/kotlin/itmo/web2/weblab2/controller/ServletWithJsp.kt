package itmo.web2.weblab2.controller

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest

@WebServlet(name = ServletWithJsp.nameOfServlet)
class ServletWithJsp() : HttpServlet() {

    companion object {
        const val nameOfServlet = "servletWithJsp"
    }




    override fun service(request: ServletRequest, response: ServletResponse) {
        val arg = request.getParameter("result")
        if(!(arg.isNullOrEmpty())){
            val fileJsp = "/result.jsp"
            log("forward to result jsp")
            val dispatcher = servletContext.getRequestDispatcher(fileJsp)
            try {
                dispatcher.forward(request, response)
                log("forward is ok")
            } catch (e: Exception) {
                log(e.message)
            }
        }
        else {
            val fileJsp = "/index.jsp"
            log("forward to index jsp")
            val dispatcher = servletContext.getRequestDispatcher(fileJsp)
            try {
                dispatcher.forward(request, response)
                log("forward is ok")
            } catch (e: Exception) {
                log(e.message)
            }
        }
    }

    class DataClass(): AbstractCommandServlet() {
        override fun returnNameOfServlet(): String {
            return nameOfServlet
        }
        override fun desiredMethod(request: HttpServletRequest): Boolean {
            val pathOfRequest = request.requestURI
            val pathOfContext = request.servletContext.contextPath + "/"
            return pathOfRequest.equals(pathOfContext)
        }
    }
}