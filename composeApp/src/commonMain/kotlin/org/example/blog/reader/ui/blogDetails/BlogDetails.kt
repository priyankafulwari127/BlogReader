
import androidx.compose.runtime.Composable

@Composable
fun BlogDetails(url: String) {
    WebView(
        url = url,
        onUrlClicked = {

        },
        isLoading = {

        }
    )
}
