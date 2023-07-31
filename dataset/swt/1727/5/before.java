class PlaceHold {
  int convertToJS(Object value) {
    if (value == null) {
      return Cocoa.objc_msgSend(C_WebUndefined, S_undefined);
    }
    if (value instanceof String) {
      String result = ((String) (value));
      char[] chars = new char[result.length()];
      result.getChars(0, chars.length, chars, 0);
      return OS.CFStringCreateWithCharacters(0, chars, chars.length);
    }
    if (value instanceof Boolean) {
      int booleanValue = (((Boolean) (value)).booleanValue()) ? 1 : 0;
      return Cocoa.objc_msgSend(C_NSNumber, S_numberWithBool, booleanValue);
    }
    if (value instanceof Number) {
      double doubleValue = ((Number) (value)).doubleValue();
      return Cocoa.objc_msgSend(C_NSNumber, S_numberWithDouble, doubleValue);
    }
    if (value instanceof Object[]) {
      Object[] arrayValue = ((Object[]) (value));
      int length = arrayValue.length;
      if (length > 0) {
        int array = Cocoa.objc_msgSend(C_NSMutableArray, S_arrayWithCapacity, length);
        for (int i = 0; i < length; i++) {
          Object currentObject = arrayValue[i];
          int jsObject = convertToJS(currentObject);
          Cocoa.objc_msgSend(array, S_addObject, jsObject);
        }
        return array;
      }
    }
    SWT.error(ERROR_INVALID_RETURNVALUE);
    return 0;
  }
}
