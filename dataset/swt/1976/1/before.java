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
    if (url2.equals(URI_FILEROOT)) {
      url2 = ABOUT_BLANK;
    } else {
      length = URI_FILEROOT.length();
      if (url2.startsWith(URI_FILEROOT) && (url2.charAt(length) == '#')) {
        url2 = ABOUT_BLANK + url2.substring(length);
      }
    }
    Display display = browser.getDisplay();
    boolean top = frameID == webView.mainFrame().id;
    if (top) {
      resourceCount = 0;
      this.url = url2;
      if (url2.startsWith(ABOUT_BLANK) && (html != null)) {
        return;
      }
      Enumeration elements = functions.elements();
      while (elements.hasMoreElements()) {
        BrowserFunction function = ((BrowserFunction) (elements.nextElement()));
        execute(function.functionString);
      }
      ProgressEvent progress = new ProgressEvent(browser);
      progress.display = display;
      progress.widget = browser;
      progress.current = 1;
      progress.total = MAX_PROGRESS;
      for (int i = 0; i < progressListeners.length; i++) {
        progressListeners[i].changed(progress);
      }
      if (browser.isDisposed()) {
        return;
      }
      StatusTextEvent statusText = new StatusTextEvent(browser);
      statusText.display = display;
      statusText.widget = browser;
      statusText.text = url2;
      for (int i = 0; i < statusTextListeners.length; i++) {
        statusTextListeners[i].changed(statusText);
      }
      if (browser.isDisposed()) {
        return;
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
