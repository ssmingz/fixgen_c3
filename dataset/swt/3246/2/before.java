class PlaceHold {
  void didCommitLoadForFrame(int frame) {
    int webView = WebKit.HIWebViewGetWebView(webViewHandle);
    int dataSource = WebKit.objc_msgSend(frame, S_dataSource);
    int request = WebKit.objc_msgSend(dataSource, S_request);
    int url = WebKit.objc_msgSend(request, S_URL);
    int s = WebKit.objc_msgSend(url, S_absoluteString);
    int length = OS.CFStringGetLength(s);
    char[] buffer = new char[length];
    CFRange range = new CFRange();
    range.length = length;
    OS.CFStringGetCharacters(s, range, buffer);
    String url2 = new String(buffer);
    final Display display = getDisplay();
    boolean top = frame == WebKit.objc_msgSend(webView, S_mainFrame);
    if (top) {
      resourceCount = 0;
      this.url = url2;
      final ProgressEvent progress = new ProgressEvent(this);
      progress.display = display;
      progress.widget = this;
      progress.current = 1;
      progress.total = MAX_PROGRESS;
      for (int i = 0; i < progressListeners.length; i++) {
        final ProgressListener listener = progressListeners[i];
        display.asyncExec(
            new Runnable() {
              public void run() {
                if ((!display.isDisposed()) && (!isDisposed())) {
                  listener.changed(progress);
                }
              }
            });
      }
      StatusTextEvent statusText = new StatusTextEvent(this);
      statusText.display = display;
      statusText.widget = this;
      statusText.text = url2;
      for (int i = 0; i < statusTextListeners.length; i++) {
        statusTextListeners[i].changed(statusText);
      }
    }
    LocationEvent location = new LocationEvent(this);
    location.display = display;
    location.widget = this;
    location.location = url2;
    location.top = top;
    for (int i = 0; i < locationListeners.length; i++) {
      locationListeners[i].changed(location);
    }
  }
}
