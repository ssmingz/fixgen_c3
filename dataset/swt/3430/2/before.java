class PlaceHold {
  static int JSObjectCallAsFunctionProc(
      int ctx, int function, int thisObject, int argumentCount, int arguments, int exception) {
    if (WebKitGTK.JSValueIsObjectOfClass(ctx, thisObject, ExternalClass) == 0) {
      return WebKitGTK.JSValueMakeUndefined(ctx);
    }
    int ptr = WebKitGTK.JSObjectGetPrivate(thisObject);
    int[] handle = new int[1];
    C.memmove(handle, ptr, PTR_SIZEOF);
    Browser browser = findBrowser(handle[0]);
    if (browser == null) {
      return 0;
    }
    WebKit webkit = ((WebKit) (browser.webBrowser));
    return webkit.callJava(ctx, function, thisObject, argumentCount, arguments, exception);
  }
}
