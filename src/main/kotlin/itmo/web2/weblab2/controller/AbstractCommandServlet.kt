package itmo.web2.weblab2.controller

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest


abstract class AbstractCommandServlet(){
    abstract fun desiredMethod(request: HttpServletRequest):Boolean
    abstract fun returnNameOfServlet():String
}