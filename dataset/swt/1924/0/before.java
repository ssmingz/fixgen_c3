class PlaceHold {
  void decidePolicyForNavigationAction(
      int actionInformation, int request, int frame, int listener) {
    int url = Cocoa.objc_msgSend(request, S_URL);
    if (url == 0) {
      Cocoa.objc_msgSend(listener, S_ignore);
      return;
    }
    boolean isFileURL = Cocoa.objc_msgSend(url, S_isFileURL) != 0;
    if ((isFileURL && getUrl().startsWith(ABOUT_BLANK)) && untrustedText) {
      Cocoa.objc_msgSend(listener, S_ignore);
      return;
    }
    int s = Cocoa.objc_msgSend(url, S_absoluteString);
    int emptyString = Cocoa.objc_msgSend(C_NSString, S_string);
    s = OS.CFURLCreateStringByReplacingPercentEscapes(0, s, emptyString);
    OS.CFRelease(emptyString);
    int length = OS.CFStringGetLength(s);
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
    if (url2.startsWith(URI_APPLEWEBDATA)) {
      Cocoa.objc_msgSend(listener, S_use);
    } else {
      LocationEvent newEvent = new LocationEvent(browser);
      newEvent.display = browser.getDisplay();
      newEvent.widget = browser;
      newEvent.location = url2;
      newEvent.doit = true;
      if (locationListeners != null) {
        changingLocation = true;
        for (int i = 0; i < locationListeners.length; i++) {
          locationListeners[i].changing(newEvent);
        }
        changingLocation = false;
      }
      if (newEvent.doit) {
        if (jsEnabledChanged) {
          jsEnabledChanged = false;
          if (preferences == 0) {
            preferences = Cocoa.objc_msgSend(C_WebPreferences, S_alloc);
            Cocoa.objc_msgSend(preferences, S_init);
            Cocoa.objc_msgSend(webView, S_setPreferences, preferences);
          }
          Cocoa.objc_msgSend(preferences, S_setJavaScriptEnabled, jsEnabled ? 1 : 0);
        }
        lastNavigateURL = url2;
      }
      Cocoa.objc_msgSend(listener, newEvent.doit ? Cocoa.S_use : Cocoa.S_ignore);
    }
    if ((html != null) && (!browser.isDisposed())) {
      String html = this.html;
      this.html = null;
      _setText(html, !untrustedText);
    }
  }
}
