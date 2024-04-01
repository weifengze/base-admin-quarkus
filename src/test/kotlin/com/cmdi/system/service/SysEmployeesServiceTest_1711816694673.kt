import com.cmdi.common.utils.HttpUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await

fun main() = runBlocking {

    val url = "https://tenapi.cn/v2/bilihot"
    val requestBody = "{\"key\":\"value\"}"

    // 调用sendPostRequest函数
    val futureResponse = HttpUtils.sendPostRequest(url, requestBody)

    // 使用async + await处理异步操作
    val response = async(Dispatchers.IO) {
        futureResponse.await() // 等待CompletableFuture完成
    }

    // 获取并处理响应
    val httpResponse = response.await()
    println("Response Status Code: ${httpResponse.statusCode()}")
    println("Response Body: ${httpResponse.body()}")

    // 如果需要处理异常，可以使用try/catch
    try {
        val result = response.await()
        // ...
    } catch (e: Exception) {
        println("Error in sending POST request: ${e.message}")
    }
}
