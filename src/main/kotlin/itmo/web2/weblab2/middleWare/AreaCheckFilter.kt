package itmo.web2.weblab2.middleWare

import itmo.web2.weblab2.controller.AreaCheckServlet
import itmo.web2.weblab2.service.ValidationOfPoints
import jakarta.servlet.*
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebFilter(servletNames = [AreaCheckServlet.nameOfServlet], dispatcherTypes = [DispatcherType.FORWARD, DispatcherType.REQUEST], filterName = AreaCheckFilter.NAME)
class AreaCheckFilter : HttpFilter(){
    companion object{
        const val NAME = "AreaCheckFilter"
    }
    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            ValidationOfPoints.takeDoubleValue(request, "x")
            ValidationOfPoints.takeDoubleValue(request, "y")
            ValidationOfPoints.takeDoubleValue(request, "r")
            servletContext.log("Filter $NAME")
            chain.doFilter(request, response);
        } catch (e: Exception) {
            response.sendError(400, e.message)
        }
    }

}