class PlaceHold {
  void didFinishLoadForFrame(int frame) {
    int webView = WebKit.HIWebViewGetWebView(webViewHandle);
    if (frame == WebKit.objc_msgSend(webView, S_mainFrame)) {
      final Display display = getDisplay();
      final ProgressEvent progress = new ProgressEvent(this);
      progress.current = MAX_PROGRESS;
      progress.total = MAX_PROGRESS;
      for (int i = 0; i < progressListeners.length; i++) {
        final ProgressListener listener = progressListeners[i];
        display.asyncExec(
            new Runnable() {
              public void run() {
                if ((!display.isDisposed()) && (!isDisposed())) {
                  listener.completed(progress);
                }
              }
            });
      }
      identifier = 0;
    }
  }
}
