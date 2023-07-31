class PlaceHold {
  static int Proc(int handle, int arg0, int arg1, int arg2, int arg3, int user_data) {
    Browser browser = FindBrowser(handle);
    if (browser == null) {
      return 0;
    }
    WebKit webkit = ((WebKit) (browser.webBrowser));
    return webkit.webViewProc(handle, arg0, arg1, arg2, arg3, user_data);
  }
}
