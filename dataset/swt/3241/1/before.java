class PlaceHold {
  static int browserProc(int id, int sel, int arg0, int arg1, int arg2, int arg3) {
    Display d = Display.getCurrent();
    if ((d == null) || d.isDisposed()) {
      return 0;
    }
    Widget widget = d.findWidget(id);
    if (widget == null) {
      return 0;
    }
    WebKit webKit = ((WebKit) (((Browser) (widget)).webBrowser));
    if (sel == OS.sel_webView_resource_didFailLoadingWithError_fromDataSource_) {
      webKit.webView_resource_didFailLoadingWithError_fromDataSource(arg0, arg1, arg2, arg3);
    } else if (sel == OS.sel_webView_resource_didReceiveAuthenticationChallenge_fromDataSource_) {
      webKit.webView_resource_didReceiveAuthenticationChallenge_fromDataSource(
          arg0, arg1, arg2, arg3);
    }
    return 0;
  }
}
