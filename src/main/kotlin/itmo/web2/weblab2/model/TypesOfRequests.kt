package itmo.web2.weblab2.model

import itmo.web2.weblab2.controller.ClearTheHistory
import itmo.web2.weblab2.controller.ServletWithJsp
import jakarta.servlet.http.HttpServletRequest
import itmo.web2.weblab2.controller.*
import javax.net.ssl.HttpsURLConnection

class TypesOfRequests {
    //    val one: (HttpServletRequest)->String? = {request ->  request.getParameter("shoot")}
    companion object {

        // TODO: создание функций для проверки типов
        val areaCheckServlet = fun(request: HttpServletRequest): String? {
            val arg: String? = request.getParameter("shoot")
            return if (!(arg.isNullOrEmpty())) {
                AreaCheckServlet.nameOfServlet
            } else {
                null
            }
        }

        val clearTheHistory = fun(request: HttpServletRequest): String? {
            val arg = request.getParameter("clear")
            // TODO: val email = values["email"] ?: throw IllegalStateException("Email is missing!") пример
            return if (!(arg.isNullOrEmpty())) {
                ClearTheHistory.nameOfServlet
            } else {
                null
            }
        }

        val getDataForJsp = fun(request: HttpServletRequest): String? {
            return if (request.requestURI.contains("static/")) {
                GetDataFotJsp.nameOfServlet
            } else {
                null
            }
        }

        val servletWithJsp = fun(request: HttpServletRequest): String? {
            val pathOfRequest = request.requestURI
            val pathOfContext = request.servletContext.contextPath + "/"
            return if (pathOfRequest.equals(pathOfContext)) {
                ServletWithJsp.nameOfServlet
            } else {
                null
            }
        }
    }
}