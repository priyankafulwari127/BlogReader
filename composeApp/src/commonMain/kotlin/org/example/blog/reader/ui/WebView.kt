import androidx.compose.runtime.Composable

@Composable
expect fun WebView(
    url: String,
    isLoading: (isLoading: Boolean) -> Unit,
    onUrlClicked: (url: String) -> Unit
)