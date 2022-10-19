package itmo.web2.weblab2.controller

import itmo.web2.weblab2.model.CollectionWithDataPoints
import itmo.web2.weblab2.service.ValidationOfPoints.Companion.checkDataFromForm
import itmo.web2.weblab2.service.ValidationOfPoints.Companion.checkTheShoot
import itmo.web2.weblab2.service.ValidationOfPoints.Companion.takeDoubleValue
import itmo.web2.weblab2.service.exception.NotCanvasException
import jakarta.servlet.DispatcherType
import jakarta.servlet.annotation.WebFilter
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = AreaCheckServlet.nameOfServlet)
class AreaCheckServlet : HttpServlet() {
    companion object {
        const val nameOfServlet = " areaCheckServer"
    }
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        val out = response.writer
        val start = System.nanoTime()
        try {
            val x= takeDoubleValue(request, "x")
            val y = takeDoubleValue(request, "y")
            val r = takeDoubleValue(request, "r")
            val entry = checkTheShoot(x,y,r)
            val execTime = System.nanoTime() - start
            val message =
                CollectionWithDataPoints.DataWithPoints(x, y, r, entry.toString(), execTime.toString())
            val session= request.session
            var data = session.getAttribute("shots") as? CollectionWithDataPoints
            if (data == null) {
                data = CollectionWithDataPoints()
                session.setAttribute("shots", data)
            }
            data.collectionWithDataPoints.add(message)
            session.setAttribute("shots", data)
            out.print("No problem")
        } catch (e: Exception) {
            response.sendError(400, e.message)
        }
        // TODO: создание единого коннектора с jsp
    }

    class DataClass : AbstractCommandServlet() {
        override fun returnNameOfServlet(): String {
            return nameOfServlet
        }

        override fun desiredMethod(request: HttpServletRequest): Boolean {
            val arg: String? = request.getParameter("shoot")
            return !(arg.isNullOrEmpty())
        }
    }
}