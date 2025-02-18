package org.example.blog.reader.data.remote

import Blog
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class BlogApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchBlogs(): List<Blog> {
        val url = "https://blog.vrid.in/wp-json/wp/v2/posts?per_page=10&page=2"
        val response: HttpResponse = client.get(url)
        return response.body()
    }
}