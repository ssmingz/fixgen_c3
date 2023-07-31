class PlaceHold {
  void webView_decidePolicyForNavigationAction_request_frame_decisionListener(
      int sender, int actionInformation, int request, int frame, int listenerID) {
    NSURL url = new NSURLRequest(request).URL();
    WebPolicyDecisionListener listener = new WebPolicyDecisionListener(listenerID);
    if (loadingText) {
      listener.use();
      return;
    }
    if (url == null) {
      listener.ignore();
      return;
    }
    if ((url.isFileURL() && getUrl().startsWith(ABOUT_BLANK)) && untrustedText) {
      listener.ignore();
      return;
    }
    NSString s = url.absoluteString();
    int ptr =
        OS.CFURLCreateStringByReplacingPercentEscapesUsingEncoding(
            0, s.id, NSString.string().id, kCFStringEncodingUTF8);
    String url2 = new NSString(ptr).getString();
    OS.CFRelease(ptr);
    if (url2.equals(URI_FILEROOT)) {
      url2 = ABOUT_BLANK;
    } else {
      int length = URI_FILEROOT.length();
      if (url2.startsWith(URI_FILEROOT) && (url2.charAt(length) == '#')) {
        url2 = ABOUT_BLANK + url2.substring(length);
      }
    }
    LocationEvent newEvent = new LocationEvent(browser);
    newEvent.display = browser.getDisplay();
    newEvent.widget = browser;
    newEvent.location = url2;
    newEvent.doit = true;
    if (locationListeners != null) {
      for (int i = 0; i < locationListeners.length; i++) {
        locationListeners[i].changing(newEvent);
      }
    }
    if (newEvent.doit) {
      if (jsEnabledChanged) {
        jsEnabledChanged = false;
        if (preferences == null) {
          preferences = ((WebPreferences) (new WebPreferences().alloc().init()));
          webView.setPreferences(preferences);
        }
        preferences.setJavaScriptEnabled(jsEnabled);
      }
      listener.use();
      lastNavigateURL = url2;
    } else {
      listener.ignore();
    }
  }
}
