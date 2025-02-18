package org.example.blog.reader.ui.blogList

import Blog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.blog.reader.data.remote.BlogApi

class BlogListViewModel: ViewModel() {
    private val blogApi = BlogApi()

    private val _blogs = MutableStateFlow<List<Blog>>(emptyList())
    val blogs : StateFlow<List<Blog>> = _blogs

    fun loadBlogs () {
        viewModelScope.launch {
            try {
                _blogs.value = blogApi.fetchBlogs()
            }catch (e: Exception){
                println(e.message)
            }
        }
    }
}