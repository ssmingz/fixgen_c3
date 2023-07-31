class PlaceHold {
  WebBrowser createWebBrowser(int style) {
    if (OS.IsWinCE && ((style & (SWT.MOZILLA | SWT.WEBKIT)) != 0)) {
      throw new SWTError(SWT.ERROR_NO_HANDLES, "Unsupported Browser type");
    }
    if ((style & SWT.MOZILLA) != 0) {
      return new Mozilla();
    }
    if ((style & SWT.WEBKIT) != 0) {
      return new WebKit();
    }
    return new IE();
  }
}
