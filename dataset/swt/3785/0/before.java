class PlaceHold {
  int callJava(int ctx, int func, int thisObject, int argumentCount, int arguments, int exception) {
    Object returnValue = null;
    if (argumentCount == 3) {
      int[] result = new int[1];
      C.memmove(result, arguments, PTR_SIZEOF);
      int type = WebKit_win32.JSValueGetType(ctx, result[0]);
      if (type == WebKit_win32.kJSTypeNumber) {
        int index = ((Double) (convertToJava(ctx, result[0]))).intValue();
        result[0] = 0;
        if (index > 0) {
          Object key = new Integer(index);
          C.memmove(result, arguments + C.PTR_SIZEOF, PTR_SIZEOF);
          type = WebKit_win32.JSValueGetType(ctx, result[0]);
          if (type == WebKit_win32.kJSTypeNumber) {
            long token = ((Double) (convertToJava(ctx, result[0]))).longValue();
            BrowserFunction function = ((BrowserFunction) (functions.get(key)));
            if ((function != null) && (token == function.token)) {
              try {
                C.memmove(result, arguments + (2 * C.PTR_SIZEOF), PTR_SIZEOF);
                Object temp = convertToJava(ctx, result[0]);
                if (temp instanceof Object[]) {
                  Object[] args = ((Object[]) (temp));
                  try {
                    returnValue = function.function(args);
                  } catch (Exception e) {
                    returnValue = WebBrowser.CreateErrorString(e.getLocalizedMessage());
                  }
                }
              } catch (IllegalArgumentException e) {
                if (function.isEvaluate) {
                  function.function(
                      new String[] {
                        WebBrowser.CreateErrorString(
                            new SWTException(SWT.ERROR_INVALID_RETURN_VALUE).getLocalizedMessage())
                      });
                }
                returnValue = WebBrowser.CreateErrorString(e.getLocalizedMessage());
              }
            }
          }
        }
      }
    }
    return convertToJS(ctx, returnValue);
  }
}
