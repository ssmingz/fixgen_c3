class PlaceHold {
  int identifierForInitialRequest(int request, int dataSource) {
    final Display display = browser.getDisplay();
    final ProgressEvent progress = new ProgressEvent(browser);
    progress.display = display;
    progress.widget = browser;
    progress.current = resourceCount;
    progress.total = Math.max(resourceCount, MAX_PROGRESS);
    for (int i = 0; i < progressListeners.length; i++) {
      final ProgressListener listener = progressListeners[i];
      display.asyncExec(
          new Runnable() {
            public void run() {
              if ((!display.isDisposed()) && (!browser.isDisposed())) {
                listener.changed(progress);
              }
            }
          });
    }
    int identifier = Cocoa.objc_msgSend(C_NSNumber, S_numberWithInt, resourceCount++);
    if (this.identifier == 0) {
      int webView = Cocoa.HIWebViewGetWebView(webViewHandle);
      int frame = Cocoa.objc_msgSend(dataSource, S_webFrame);
      if (frame == Cocoa.objc_msgSend(webView, S_mainFrame)) {
        this.identifier = identifier;
      }
    }
    return identifier;
  }
}
