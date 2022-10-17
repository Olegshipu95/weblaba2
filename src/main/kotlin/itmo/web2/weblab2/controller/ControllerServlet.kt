package itmo.web2.weblab2.controller

import itmo.web2.weblab2.model.CollectionWithDataPoints
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession

@WebServlet(name = ControllerServlet.name)
class ControllerServlet : HttpServlet() {
    companion object {
        const val name = "controllerServlet"
    }

    private lateinit var message: String
    private val servlets: ArrayList<AbstractCommandServlet> = arrayListOf()

    override fun init() {
        servlets.add(AreaCheckServlet.DataClass())
        servlets.add(GetDataFotJsp.DataClass())
        servlets.add(ClearTheHistory.DataClass())
        servlets.add(ServletWithJsp.DataClass())
    }

    public override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        resendARequest(request, response)
    }

    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        resendARequest(request, response)
    }

    private fun resendARequest(request: HttpServletRequest, response: HttpServletResponse) {
        val session: HttpSession = request.session
        var data: CollectionWithDataPoints? = session.getAttribute("shots") as CollectionWithDataPoints?
        if (data == null) {
            data = CollectionWithDataPoints()
            session.setAttribute("shots", data)
        }
        log(request.requestURL.toString())
        for (item in servlets) {
            if (item.desiredMethod(request)) {
                log("send to ${item.returnNameOfServlet()}")
                servletContext.getNamedDispatcher(item.returnNameOfServlet()).forward(request, response)
                return
            }
        }
        servletContext.getRequestDispatcher("/ThePageIsNotExist.jsp").forward(request, response)
    }
}