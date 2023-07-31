class PlaceHold {
  public boolean setUrl(String url) {
    html = null;
    if (url.startsWith(PROTOCOL_FILE)) {
      url = url.substring(PROTOCOL_FILE.length());
    }
    int selector = Cocoa.S_fileURLWithPath;
    boolean isHttpURL = url.indexOf('/') != 0;
    if (isHttpURL) {
      selector = Cocoa.S_URLWithString;
      if (url.indexOf(':') == (-1)) {
        url = (PROTOCOL_HTTP + "//") + url;
      }
    }
    int length = url.length();
    char[] chars = new char[length];
    url.getChars(0, length, chars, 0);
    int sHandle = OS.CFStringCreateWithCharacters(0, chars, length);
    int inURL = Cocoa.objc_msgSend(C_NSURL, selector, sHandle);
    OS.CFRelease(sHandle);
    if (inURL == 0) {
      return false;
    }
    int request = Cocoa.objc_msgSend(C_NSURLRequest, S_requestWithURL, inURL);
    int webView = Cocoa.HIWebViewGetWebView(webViewHandle);
    int mainFrame = Cocoa.objc_msgSend(webView, S_mainFrame);
    Cocoa.objc_msgSend(mainFrame, S_loadRequest, request);
    return true;
  }
}
