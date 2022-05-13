package com.bithumb.tide.common.logger

import com.fasterxml.jackson.databind.ObjectMapper
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@Aspect
@Component
class ControllerLogger {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val mapper = ObjectMapper()

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    fun restControllerPointcut() {}

    @Around("restControllerPointcut()")
    fun doLogging(joinPoint: ProceedingJoinPoint): Any? {

        logger.info("Controller Request - ${getRequestLog(joinPoint)}")

        val startAt = System.currentTimeMillis()

        val result: Any?
        try {
            result = joinPoint.proceed()
        } catch (e: Exception) {
            val endAt = System.currentTimeMillis()
            logger.error("Controller Fail - ${getFailLog(joinPoint, startAt, endAt)}")
            throw e
        }

        val endAt = System.currentTimeMillis()

        logger.info("Controller Response - ${getResponseLog(joinPoint, result, startAt, endAt)}")

        return result
    }

    private fun getRequestLog(joinPoint: ProceedingJoinPoint): LinkedHashMap<String, Any> {
        val httpRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val requestLog = getCommonLog(joinPoint, httpRequest)

        requestLog["requestHeader"] = mapper.writeValueAsString(getRequestHeaderAsMap(httpRequest))
        if (joinPoint.args.isNotEmpty()) {
            requestLog["requestBody"] = mapper.writeValueAsString(joinPoint.args)
        }

        return requestLog
    }

    private fun getResponseLog(
        joinPoint: ProceedingJoinPoint,
        result: Any?,
        startAt: Long,
        endAt: Long,
    ): LinkedHashMap<String, Any> {
        val httpRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val responseLog = getCommonLog(joinPoint, httpRequest)

        result?.let { responseLog["response"] = mapper.writeValueAsString(it) }
        responseLog["executionTime"] = "${endAt - startAt}ms"

        return responseLog
    }

    private fun getFailLog(joinPoint: ProceedingJoinPoint, startAt: Long, endAt: Long): LinkedHashMap<String, Any> {
        val httpRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val failLog = getCommonLog(joinPoint, httpRequest)

        failLog["executionTime"] = "${endAt - startAt}ms"

        return failLog
    }

    private fun getCommonLog(
        joinPoint: ProceedingJoinPoint,
        httpRequest: HttpServletRequest
    ): LinkedHashMap<String, Any> {
        return linkedMapOf(
            "localDateTime" to LocalDateTime.now(),
            "controller" to joinPoint.signature.declaringTypeName,
            "method" to joinPoint.signature.name,
            "httpMethod" to httpRequest.method,
            "requestUri" to httpRequest.requestURI,
        )
    }

    private fun getRequestHeaderAsMap(httpRequest: HttpServletRequest): LinkedHashMap<String, String> {
        val requestHeader = LinkedHashMap<String, String>()
        val headerNames = httpRequest.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement()
            requestHeader[headerName] = httpRequest.getHeader(headerName)
        }
        return requestHeader
    }
}