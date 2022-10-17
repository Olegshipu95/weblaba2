package itmo.web2.weblab2.controller

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = GetDataFotJsp.nameOfServlet)
class GetDataFotJsp : HttpServlet() {

    companion object{
        const val nameOfServlet = "getDataForJsp"
    }
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val uri  = request.requestURI
        val trueUri = uri.replace(servletContext.contextPath, "")
        response.contentType = servletContext.getMimeType(trueUri)
        servletContext.getResourceAsStream(trueUri).transferTo(response.outputStream)
    }

    class DataClass : AbstractCommandServlet() {
        override fun returnNameOfServlet(): String {
            return nameOfServlet
        }
        override fun desiredMethod(request: HttpServletRequest): Boolean {
            return request.requestURI.contains("static/")
        }
    }
}