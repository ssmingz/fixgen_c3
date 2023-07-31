class PlaceHold {
  void _setText(String html, boolean untrusted) {
    NSString string = NSString.stringWith(html);
    NSString URLString;
    if (untrusted) {
      URLString = NSString.stringWith(ABOUT_BLANK);
    } else {
      URLString = NSString.stringWith(URI_FILEROOT);
    }
    NSURL URL = NSURL.URLWithString(URLString);
    WebFrame mainFrame = webView.mainFrame();
    mainFrame.loadHTMLString(string, URL);
  }
}
