class PlaceHold {
  public static boolean setCookie(String value, String url) {
    if (value == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (url == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    return WebBrowser.SetCookie(value, url, true);
  }
}
