class PlaceHold {
  static int Proc(int handle, int arg0, int arg1, int user_data) {
    Browser browser = findBrowser(handle);
    if (browser == null) {
      return 0;
    }
    WebKit webkit = ((WebKit) (browser.webBrowser));
    return webkit.webViewProc(handle, arg0, arg1, user_data);
  }
}
