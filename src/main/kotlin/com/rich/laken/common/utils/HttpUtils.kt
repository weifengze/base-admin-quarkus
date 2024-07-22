package com.rich.laken.common.utils

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.concurrent.CompletableFuture


class HttpUtils {

    companion object {

        private val httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10)) // 设置默认连接超时
            .build()

        /**
         * 发送 POST 请求并获取响应
         *
         * @param url 请求的目标 URL
         * @param requestBody 请求体（JSON 格式字符串）
         * @return 响应的未来对象，包含状态码和响应体
         */
        fun sendPostRequest(url: String, requestBody: String): CompletableFuture<HttpResponse<String>> {
            // 准备请求体
            val bodyPublisher = HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8)

            // 构建 POST 请求
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(bodyPublisher)
                .header("Content-Type", "application/json") // 默认设置 JSON 格式请求体
                .build()

            // 异步发送请求并获取响应
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        }

        /**
         * 发送 POST 请求并获取响应
         *
         * @param url 请求的目标 URL
         * @param requestBody 请求体（JSON 格式字符串）
         * @param contentType 请求体的内容类型
         * @return 响应的未来对象，包含状态码和响应体
         */
        fun sendPostRequest(
            url: String,
            requestBody: String,
            contentType: String
        ): CompletableFuture<HttpResponse<String>> {
            // 准备请求体
            val bodyPublisher = HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8)

            // 构建 POST 请求
            val request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(bodyPublisher)
                .header("Content-Type", contentType) // 默认设置 JSON 格式请求体
                .build()

            // 异步发送请求并获取响应
            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        }
    }
}



