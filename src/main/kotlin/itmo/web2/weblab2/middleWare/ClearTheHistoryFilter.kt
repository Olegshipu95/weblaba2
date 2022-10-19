package itmo.web2.weblab2.middleWare

import itmo.web2.weblab2.controller.ClearTheHistory
import itmo.web2.weblab2.model.CollectionWithDataPoints
import jakarta.servlet.DispatcherType
import jakarta.servlet.FilterChain
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import java.lang.reflect.Modifier

@WebFilter(servletNames = [ClearTheHistory.nameOfServlet], dispatcherTypes = [DispatcherType.FORWARD], filterName = ClearTheHistoryFilter.NAME)
class ClearTheHistoryFilter:HttpFilter(){
    companion object{
        const val NAME = "ClearTheHistoryFilter"
    }
    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            servletContext.log("interception by ${AreaCheckFilter.NAME}")
            val session: HttpSession = request.session
            var data: CollectionWithDataPoints? = session.getAttribute("shots") as CollectionWithDataPoints?
            if (data == null) {
                data = CollectionWithDataPoints()
                session.setAttribute("shots", data)
            }
            chain.doFilter(request, response);
        } catch (e: Exception) {
            response.sendError(400, e.message)
        }
    }
}