class PlaceHold {
  void _setText(String html) {
    int length = html.length();
    char[] buffer = new char[length];
    html.getChars(0, length, buffer, 0);
    int string = OS.CFStringCreateWithCharacters(0, buffer, length);
    length = URI_FROMMEMORY.length();
    buffer = new char[length];
    URI_FROMMEMORY.getChars(0, length, buffer, 0);
    int URLString = OS.CFStringCreateWithCharacters(0, buffer, length);
    int URL = Cocoa.objc_msgSend(C_NSURL, S_URLWithString, URLString);
    OS.CFRelease(URLString);
    int webView = Cocoa.HIWebViewGetWebView(webViewHandle);
    int mainFrame = Cocoa.objc_msgSend(webView, S_mainFrame);
    Cocoa.objc_msgSend(mainFrame, S_loadHTMLStringbaseURL, string, URL);
    OS.CFRelease(string);
  }
}
