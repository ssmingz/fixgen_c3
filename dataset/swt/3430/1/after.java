class PlaceHold {
  static int Proc(int handle, int user_data) {
    Browser browser = FindBrowser(handle);
    if (browser == null) {
      return 0;
    }
    WebKit webkit = ((WebKit) (browser.webBrowser));
    return webkit.webViewProc(handle, user_data);
  }
}
