class PlaceHold {
  long webkit_window_object_cleared(long web_view, long frame, long context, long window_object) {
    long globalObject = WebKitGTK.JSContextGetGlobalObject(context);
    long externalObject = WebKitGTK.JSObjectMake(context, ExternalClass, webViewData);
    byte[] bytes = null;
    try {
      bytes = (OBJECTNAME_EXTERNAL + '\u0000').getBytes(CHARSET_UTF8);
    } catch (UnsupportedEncodingException e) {
      bytes = Converter.wcsToMbcs(null, OBJECTNAME_EXTERNAL, true);
    }
    long name = WebKitGTK.JSStringCreateWithUTF8CString(bytes);
    WebKitGTK.JSObjectSetProperty(context, globalObject, name, externalObject, 0, null);
    WebKitGTK.JSStringRelease(name);
    Enumeration<BrowserFunction> elements = functions.elements();
    while (elements.hasMoreElements()) {
      BrowserFunction current = elements.nextElement();
      execute(current.functionString);
    }
    long mainFrame = WebKitGTK.webkit_web_view_get_main_frame(webView);
    boolean top = mainFrame == frame;
    addEventHandlers(web_view, top);
    return 0;
  }
}
