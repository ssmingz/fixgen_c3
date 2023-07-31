class PlaceHold {
  void didCommitLoadForFrame(int frame) {
    int dataSource = Cocoa.objc_msgSend(frame, S_dataSource);
    int request = Cocoa.objc_msgSend(dataSource, S_request);
    int url = Cocoa.objc_msgSend(request, S_URL);
    int s = Cocoa.objc_msgSend(url, S_absoluteString);
    int length = OS.CFStringGetLength(s);
    if (length == 0) {
      return;
    }
    int emptyString = Cocoa.objc_msgSend(C_NSString, S_string);
    s = OS.CFURLCreateStringByReplacingPercentEscapes(0, s, emptyString);
    OS.CFRelease(emptyString);
    length = OS.CFStringGetLength(s);
    char[] buffer = new char[length];
    CFRange range = new CFRange();
    range.length = length;
    OS.CFStringGetCharacters(s, range, buffer);
    String url2 = new String(buffer);
    if (url2.equals(URI_FILEROOT)) {
      url2 = ABOUT_BLANK;
    } else {
      length = URI_FILEROOT.length();
      if (url2.startsWith(URI_FILEROOT) && (url2.charAt(length) == '#')) {
        url2 = ABOUT_BLANK + url2.substring(length);
      }
    }
    final Display display = browser.getDisplay();
    boolean top = frame == Cocoa.objc_msgSend(webView, S_mainFrame);
    if (top) {
      resourceCount = 0;
      this.url = url2;
      final ProgressEvent progress = new ProgressEvent(browser);
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
