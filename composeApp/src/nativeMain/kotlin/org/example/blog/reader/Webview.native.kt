import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSMutableURLRequest.Companion.requestWithURL
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.UIKit.UIView
import platform.WebKit.WKNavigationAction
import platform.WebKit.WKNavigationActionPolicy
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKNavigationTypeLinkActivated
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject

@Composable
actual fun WebView(
    url: String,
    isLoading: (isLoading: Boolean) -> Unit,
    onUrlClicked: (url: String) -> Unit
) {
    val loadUrl = remember { url }
    val webView = remember { WKWebView() }

    // Define the WKNavigationDelegate
    val navigationDelegate = rememberWebViewDelegate(onUrlClicked)

    // Set WKNavigationDelegate to the webView
    webView.navigationDelegate = navigationDelegate
    UIKitView(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        factory = {
            val container = UIView()
            webView.apply {
                WKWebViewConfiguration().apply {
                    allowsInlineMediaPlayback = true
                    allowsAirPlayForMediaPlayback = true
                    allowsPictureInPictureMediaPlayback = true
                }
                loadRequest(NSURLRequest.requestWithURL(NSURL(loadUrl)))
            }
            container.addSubview(webView)
            container
        }
    )
}

@Composable
private fun rememberWebViewDelegate(onUrlClicked: (String) -> Unit): WKNavigationDelegateProtocol {
    return object : NSObject(), WKNavigationDelegateProtocol {
        override fun webView(
            webView: WKWebView,
            decidePolicyForNavigationAction: WKNavigationAction,
            decisionHandler: (WKNavigationActionPolicy) -> Unit
        ) {
            val navigationType = decidePolicyForNavigationAction.navigationType
            val request = decidePolicyForNavigationAction.request

            when (navigationType) {
                WKNavigationTypeLinkActivated -> {
                    // Handle link clicks
                    if (decidePolicyForNavigationAction.targetFrame == null) {
                        // Load the link in the same WKWebView
                        webView.loadRequest(request)
                    }
                    onUrlClicked(request.URL?.absoluteString ?: "")
                    println(request.URL?.absoluteString ?: "")
                    decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
                }
                else -> decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
            }
        }
    }
}