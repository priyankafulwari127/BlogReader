package org.example.blog.reader.ui.blogList

import Blog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.blog.reader.data.remote.BlogApi

class BlogListViewModel: ViewModel() {
    private val blogApi = BlogApi()

    private val _blogs = MutableStateFlow<List<Blog>>(emptyList())
    val blogs : StateFlow<List<Blog>> = _blogs

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded = _isLoaded.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _pageNo = MutableStateFlow(0)

    fun loadBlogs () {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _pageNo.value += 1
                val result = blogApi.fetchBlogs(pageNo = _pageNo.value)
                if (result.size>10) {
                    //if size of page is not equal to 10 or less then 10, so all item is loaded
                    _isLoaded.value = true
                }
                _isLoading.value = false
                _blogs.value += result
            }catch (e: Exception){
                _isLoading.value = false
                println(e.message)
            }
        }
    }
}