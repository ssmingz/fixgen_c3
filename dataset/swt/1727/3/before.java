class PlaceHold {
  NSObject convertToJS(Object value) {
    if (value == null) {
      return WebUndefined.undefined();
    }
    if (value instanceof String) {
      return NSString.stringWith(((String) (value)));
    }
    if (value instanceof Boolean) {
      return NSNumber.numberWithBool(((Boolean) (value)).booleanValue());
    }
    if (value instanceof Number) {
      return NSNumber.numberWithDouble(((Number) (value)).doubleValue());
    }
    if (value instanceof Object[]) {
      Object[] arrayValue = ((Object[]) (value));
      int length = arrayValue.length;
      if (length > 0) {
        NSMutableArray array = NSMutableArray.arrayWithCapacity(length);
        for (int i = 0; i < length; i++) {
          Object currentObject = arrayValue[i];
          array.addObject(convertToJS(currentObject));
        }
        return array;
      }
    }
    SWT.error(ERROR_INVALID_RETURNVALUE);
    return null;
  }
}
