class PlaceHold {
  Object convertToJava(int ctx, int value) {
    int type = WebKit_win32.JSValueGetType(ctx, value);
    switch (type) {
      case WebKit_win32.kJSTypeBoolean:
        {
          int result = WebKit_win32.JSValueToBoolean(ctx, value);
          return new Boolean(result != 0);
        }
      case WebKit_win32.kJSTypeNumber:
        {
          double result = WebKit_win32.JSValueToNumber(ctx, value, null);
          return new Double(result);
        }
      case WebKit_win32.kJSTypeString:
        {
          int string = WebKit_win32.JSValueToStringCopy(ctx, value, null);
          if (string == 0) {
            return "";
          }
          int length = WebKit_win32.JSStringGetMaximumUTF8CStringSize(string);
          byte[] bytes = new byte[((int) (length))];
          length = WebKit_win32.JSStringGetUTF8CString(string, bytes, length);
          WebKit_win32.JSStringRelease(string);
          try {
            return new String(bytes, 0, ((int) (length)) - 1, CHARSET_UTF8);
          } catch (UnsupportedEncodingException e) {
            return new String(bytes);
          }
        }
      case WebKit_win32.kJSTypeNull:
      case WebKit_win32.kJSTypeUndefined:
        return null;
      case WebKit_win32.kJSTypeObject:
        {
          byte[] bytes = null;
          try {
            bytes = (PROPERTY_LENGTH + '\u0000').getBytes(CHARSET_UTF8);
          } catch (UnsupportedEncodingException e) {
            bytes = (PROPERTY_LENGTH + '\u0000').getBytes();
          }
          int propertyName = WebKit_win32.JSStringCreateWithUTF8CString(bytes);
          int valuePtr = WebKit_win32.JSObjectGetProperty(ctx, value, propertyName, null);
          WebKit_win32.JSStringRelease(propertyName);
          type = WebKit_win32.JSValueGetType(ctx, valuePtr);
          if (type == WebKit_win32.kJSTypeNumber) {
            int length = ((int) (WebKit_win32.JSValueToNumber(ctx, valuePtr, null)));
            Object[] result = new Object[length];
            for (int i = 0; i < length; i++) {
              int current = WebKit_win32.JSObjectGetPropertyAtIndex(ctx, value, i, null);
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
