package itmo.web2.weblab2.controller

import itmo.web2.weblab2.model.CollectionWithDataPoints
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = ClearTheHistory.nameOfServlet)
class ClearTheHistory() : HttpServlet() {

    companion object {
        const val nameOfServlet: String = "clearTheHistory"
    }

    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val data = request.session.getAttribute("shots") as CollectionWithDataPoints
        val table = data.collectionWithDataPoints
        table.clear()
        response.writer.print("all is ok")
    }

    class DataClass() : AbstractCommandServlet() {
        override fun returnNameOfServlet(): String {
            return nameOfServlet
        }

        override fun desiredMethod(request: HttpServletRequest): Boolean {
            val arg = request.getParameter("clear")
            // TODO: val email = values["email"] ?: throw IllegalStateException("Email is missing!") пример
            return !(arg.isNullOrEmpty())
        }
    }
}