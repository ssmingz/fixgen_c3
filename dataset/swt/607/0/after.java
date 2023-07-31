class PlaceHold {
  void _setText(String html, boolean trusted) {
    NSString string = NSString.stringWith(html);
    NSString URLString;
    if (trusted) {
      URLString = NSString.stringWith(URI_FILEROOT);
    } else {
      URLString = NSString.stringWith(ABOUT_BLANK);
    }
    NSURL URL = NSURL.URLWithString(URLString);
    WebFrame mainFrame = webView.mainFrame();
    mainFrame.loadHTMLString(string, URL);
  }
}
