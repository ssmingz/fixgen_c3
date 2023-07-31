class PlaceHold {
  void _setText(String html, boolean untrusted) {
    int string = createNSString(html);
    int URLString;
    if (untrusted) {
      URLString = createNSString(ABOUT_BLANK);
    } else {
      URLString = createNSString(URI_FILEROOT);
    }
    int URL = Cocoa.objc_msgSend(C_NSURL, S_URLWithString, URLString);
    OS.CFRelease(URLString);
    int mainFrame = Cocoa.objc_msgSend(webView, S_mainFrame);
    Cocoa.objc_msgSend(mainFrame, S_loadHTMLStringbaseURL, string, URL);
    OS.CFRelease(string);
  }
}
