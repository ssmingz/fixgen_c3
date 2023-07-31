class PlaceHold {
  WebBrowser createWebBrowser(int style) {
    boolean webkitInstalled = WebKit.isInstalled();
    if (((style & SWT.MOZILLA) != 0) || ((!webkitInstalled) && ((style & SWT.WEBKIT) == 0))) {
      return new Mozilla();
    }
    if (!webkitInstalled) {
      return null;
    }
    return new WebKit();
  }
}
