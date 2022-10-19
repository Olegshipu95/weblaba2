package itmo.web2.weblab2.service

import itmo.web2.weblab2.service.exception.NotCanvasException
import jakarta.servlet.ServletRequest
import java.lang.IllegalArgumentException
import kotlin.math.abs

class ValidationOfPoints {
    companion object {
        private val arrayX = arrayOf(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0)
        private val arrayR = arrayOf(1.0, 1.5, 2.0, 2.5, 3.0)

        @Throws(IllegalArgumentException::class)
        fun takeDoubleValue(request: ServletRequest, nameOfValue: String): Double {
            val value = request.getParameter(nameOfValue)
            if (value.isNullOrEmpty()) {
                throw IllegalArgumentException("The $nameOfValue is null or empty")
            }
            try {
                return value.toDouble()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("The $nameOfValue is not number")
            }
        }

        @Throws(NotCanvasException::class, IllegalArgumentException::class)
        fun checkDataFromForm(x: Double, y: Double, r: Double) {
            checkXInBounds(x)
            checkYInBounds(y)
            checkRInBounds(r)
        }

        @Throws(IllegalArgumentException::class)
        fun checkXInBounds(x: Double) {
            if (!(arrayX.contains(x))) {
                throw IllegalArgumentException("x out of bonds")
            }
        }

        @Throws(IllegalArgumentException::class)
        fun checkYInBounds(y: Double) {
            if (y >= 5 || y <= -5) {
                throw IllegalArgumentException("y out of bonds")
            }
        }

        @Throws(IllegalArgumentException::class)
        fun checkRInBounds(r: Double) {
            if (!(arrayR.contains(r))) {
                throw IllegalArgumentException("r out of bonds")
            }
        }
        fun checkTheShoot(x:Double,y:Double,r:Double):Boolean{
            return if (x >= 0 && y <= 0 && 4 * (x * x + abs(y) * abs(y)) <= r * r) {
                true
            } else if (x <= 0 && y <= 0 && abs(x) <= r && abs(y) <= r / 2) {
                true
            } else x >= 0 && y >= 0 && (x + 2 * y) <= r
        }
    }
}