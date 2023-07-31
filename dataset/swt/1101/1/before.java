class PlaceHold {
  Object convertToJava(int ctx, int value) {
    int type = WebKitGTK.JSValueGetType(ctx, value);
    switch (type) {
      case WebKitGTK.kJSTypeBoolean:
        {
          int result = WebKitGTK.JSValueToBoolean(ctx, value);
          return new Boolean(result != 0);
        }
      case WebKitGTK.kJSTypeNumber:
        {
          double result = WebKitGTK.JSValueToNumber(ctx, value, null);
          return new Double(result);
        }
      case WebKitGTK.kJSTypeString:
        {
          int string = WebKitGTK.JSValueToStringCopy(ctx, value, null);
          if (string == 0) {
            return "";
          }
          int length = WebKitGTK.JSStringGetMaximumUTF8CStringSize(string);
          byte[] bytes = new byte[((int) (length))];
          length = WebKitGTK.JSStringGetUTF8CString(string, bytes, length);
          WebKitGTK.JSStringRelease(string);
          try {
            return new String(bytes, 0, ((int) (length)) - 1, CHARSET_UTF8);
          } catch (UnsupportedEncodingException e) {
            return new String(Converter.mbcsToWcs(null, bytes));
          }
        }
      case WebKitGTK.kJSTypeNull:
      case WebKitGTK.kJSTypeUndefined:
        return null;
      case WebKitGTK.kJSTypeObject:
        {
          byte[] bytes = null;
          try {
            bytes = (PROPERTY_LENGTH + '\u0000').getBytes(CHARSET_UTF8);
          } catch (UnsupportedEncodingException e) {
            bytes = Converter.wcsToMbcs(null, PROPERTY_LENGTH, true);
          }
          int propertyName = WebKitGTK.JSStringCreateWithUTF8CString(bytes);
          int valuePtr = WebKitGTK.JSObjectGetProperty(ctx, value, propertyName, null);
          WebKitGTK.JSStringRelease(propertyName);
          type = WebKitGTK.JSValueGetType(ctx, valuePtr);
          if (type == WebKitGTK.kJSTypeNumber) {
            int length = ((int) (WebKitGTK.JSValueToNumber(ctx, valuePtr, null)));
            Object[] result = new Object[length];
            for (int i = 0; i < length; i++) {
              int current = WebKitGTK.JSObjectGetPropertyAtIndex(ctx, value, i, null);
              if (current != 0) {
                result[i] = convertToJava(ctx, current);
              }
            }
            return result;
          }
        }
    }
    SWT.error(ERROR_INVALID_ARGUMENT);
    return null;
  }
}
