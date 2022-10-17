package itmo.web2.weblab2.model

import itmo.web2.weblab2.controller.AreaCheckServlet
import itmo.web2.weblab2.controller.ClearTheHistory
import itmo.web2.weblab2.controller.ServletWithJsp
import jakarta.servlet.http.HttpServletRequest

object TypesOfRequests {
    // TODO: создание функций для проверки типов
    fun areaCheckServlet(request: HttpServletRequest): Boolean {
        val arg: String? = request.getParameter("shoot")
        return !(arg.isNullOrEmpty())
    }
    fun clearTheHistory(request: HttpServletRequest): Boolean {
        val arg = request.getParameter("clear")
        // TODO: val email = values["email"] ?: throw IllegalStateException("Email is missing!") пример
        return !(arg.isNullOrEmpty())
    }
    fun servletWithJsp(request: HttpServletRequest): Boolean {
        val pathOfRequest = request.requestURI
        val pathOfContext = request.servletContext.contextPath + "/"
        return pathOfRequest.equals(pathOfContext)
    }
}