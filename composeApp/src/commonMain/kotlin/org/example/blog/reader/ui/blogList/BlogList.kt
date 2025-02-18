package org.example.blog.reader.ui.blogList

import Blog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BlogList(
    onBlogClick: (Blog)->Unit = {},
    viewModel: BlogListViewModel = viewModel { BlogListViewModel() }
) {
    val blogs by viewModel.blogs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isLoaded by viewModel.isLoaded.collectAsState()

    LaunchedEffect(Unit){
        viewModel.loadBlogs()
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Blog List",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                }
            )
        }
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            userScrollEnabled = true,
            state = listState
        ) {
            items(blogs){ blog ->
                BlogItem(
                    blog = blog,
                    onBlogClick = onBlogClick
                )
            }
            if(isLoading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator(Modifier.size(48.dp))
                    }
                }
            }
        }
        LaunchedEffect(listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index) {
            if (isLoaded.not() && !isLoading &&
                listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == blogs.size - 1
            ) {
                viewModel.loadBlogs()
            }
        }
    }
}

@Composable
private fun BlogItem(
    blog: Blog,
    onBlogClick: (Blog) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onBlogClick(blog)
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = blog.title.rendered, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = blog.excerpt.rendered, style = MaterialTheme.typography.body2)
        }
    }
}