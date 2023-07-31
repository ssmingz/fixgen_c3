class PlaceHold {
  void webView_didCommitLoadForFrame(int sender, int frameID) {
    WebFrame frame = new WebFrame(frameID);
    WebDataSource dataSource = frame.dataSource();
    NSURLRequest request = dataSource.request();
    NSURL url = request.URL();
    NSString s = url.absoluteString();
    int length = ((int) (s.length()));
    if (length == 0) {
      return;
    }
    String url2 = s.getString();
    if (url2.equals(URI_FROMMEMORY)) {
      url2 = ABOUT_BLANK;
    }
    final Display display = browser.getDisplay();
    boolean top = frameID == webView.mainFrame().id;
    if (top) {
      resourceCount = 0;
      this.url = url2;
      Enumeration elements = functions.elements();
      while (elements.hasMoreElements()) {
        BrowserFunction function = ((BrowserFunction) (elements.nextElement()));
        execute(function.functionString);
      }
      final ProgressEvent progress = new ProgressEvent(browser);
      progress.display = display;
      progress.widget = browser;
      progress.current = 1;
      progress.total = MAX_PROGRESS;
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
      StatusTextEvent statusText = new StatusTextEvent(browser);
      statusText.display = display;
      statusText.widget = browser;
      statusText.text = url2;
      for (int i = 0; i < statusTextListeners.length; i++) {
        statusTextListeners[i].changed(statusText);
      }
    }
    LocationEvent location = new LocationEvent(browser);
    location.display = display;
    location.widget = browser;
    location.location = url2;
    location.top = top;
    for (int i = 0; i < locationListeners.length; i++) {
      locationListeners[i].changed(location);
    }
  }
}
