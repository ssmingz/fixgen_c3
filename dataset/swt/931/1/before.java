class PlaceHold {
  Object convertToJava(int value) {
    NSObject object = new NSObject(value);
    int clazz = OS.objc_lookUpClass("NSString");
    if (object.isKindOfClass(clazz)) {
      NSString string = new NSString(value);
      return string.getString();
    }
    clazz = OS.objc_lookUpClass("NSNumber");
    if (object.isKindOfClass(clazz)) {
      NSNumber number = new NSNumber(value);
      int ptr = number.objCType();
      byte[] type = new byte[1];
      OS.memmove(type, ptr, 1);
      if ((type[0] == 'c') || (type[0] == 'B')) {
        return new Boolean(number.boolValue());
      }
      if ("islqISLQfd".indexOf(type[0]) != (-1)) {
        return new Double(number.doubleValue());
      }
    }
    clazz = OS.objc_lookUpClass("WebScriptObject");
    if (object.isKindOfClass(clazz)) {
      WebScriptObject script = new WebScriptObject(value);
      id id = script.valueForKey(NSString.stringWith("length"));
      int length = new NSNumber(id).intValue();
      Object[] arguments = new Object[length];
      for (int i = 0; i < length; i++) {
        id current = script.webScriptValueAtIndex(i);
        if (current != null) {
          arguments[i] = convertToJava(current.id);
        }
      }
      return arguments;
    }
    SWT.error(ERROR_INVALID_ARGUMENT);
    return null;
  }
}
