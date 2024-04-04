package com.cmdi.framework.interceptor

import jakarta.annotation.Priority
import jakarta.inject.Inject
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import java.util.logging.Logger


@Priority(2020)
@Interceptor
class LoggingInterceptor {

    @AroundInvoke
    fun logInvocation(context: InvocationContext): Any {
        // ...log before
        val ret = context.proceed()

        // ...log after
        return ret
    }
}

//@Priority(2020)
//@Interceptor
//class TokenInterceptor {
//
//    @AroundInvoke
//    fun logInvocation(context: InvocationContext): Any {
//        // ...log before
//        val ret = context.proceed()
//
//        // ...log after
//        return ret
//    }
//
////    @RouteFilter(4000) // You can specify order of the filter if needed
////    fun checkToken(routingContext: RoutingContext): Publisher<Void> {
////        val token =
////            routingContext.request().getHeader("Authorization") // Assuming token is in the "Authorization" header
////        if (token != null && isValidToken(token)) {
////            // Token is valid, proceed with the request
////            return routingContext.vertx().{ promise ->
////                routingContext.next()
////                promise.complete()
////            }
////        } else {
////            // Token is missing or invalid, respond with unauthorized status
////            routingContext.response().setStatusCode(401).end("Unauthorized")
////            return routingContext.vertx().< Void > executeBlocking < java . lang . Void ? > { promise ->
////                promise.complete()
////            }
////        }
////    }
//
//    private fun isValidToken(token: String): Boolean {
//        return token == "your_valid_token" // Sample validation logic, replace with your own
//    }
//}
