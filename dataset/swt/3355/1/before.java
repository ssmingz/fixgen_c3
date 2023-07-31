class PlaceHold {
  int createWebViewWithRequest(int request) {
    WindowEvent newEvent = new WindowEvent(this);
    newEvent.display = getDisplay();
    newEvent.widget = this;
    if (openWindowListeners != null) {
      for (int i = 0; i < openWindowListeners.length; i++) {
        openWindowListeners[i].open(newEvent);
      }
    }
    int webView = 0;
    Browser browser = newEvent.browser;
    if ((browser != null) && (!browser.isDisposed())) {
      webView = WebKit.HIWebViewGetWebView(browser.webViewHandle);
      if (request != 0) {
        int mainFrame = WebKit.objc_msgSend(webView, S_mainFrame);
        WebKit.objc_msgSend(mainFrame, S_loadRequest, request);
      }
    }
    return webView;
  }
}
