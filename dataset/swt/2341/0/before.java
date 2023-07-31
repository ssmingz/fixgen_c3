class PlaceHold {
  int kEventAccessibleGetAllAttributeNames(int nextHandler, int theEvent, int userData) {
    int code = OS.eventNotHandledErr;
    String[] attributes = getAxAttributes();
    if (attributes != null) {
      OS.CallNextEventHandler(nextHandler, theEvent);
      nextHandler = 0;
      int[] arrayRef = new int[1];
      OS.GetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeNames,
          typeCFMutableArrayRef,
          null,
          4,
          null,
          arrayRef);
      int attributesArrayRef = arrayRef[0];
      int length = OS.CFArrayGetCount(attributesArrayRef);
      String[] osAttributes = new String[length];
      for (int i = 0; i < length; i++) {
        int stringRef = OS.CFArrayGetValueAtIndex(attributesArrayRef, i);
        int strLength = OS.CFStringGetLength(stringRef);
        char[] buffer = new char[strLength];
        CFRange range = new CFRange();
        range.length = strLength;
        OS.CFStringGetCharacters(stringRef, range, buffer);
        osAttributes[i] = new String(buffer);
      }
      for (int i = 0; i < attributes.length; i++) {
        if (!contains(osAttributes, attributes[i])) {
          String string = attributes[i];
          char[] buffer = new char[string.length()];
          string.getChars(0, buffer.length, buffer, 0);
          int stringRef =
              OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
          OS.CFArrayAppendValue(attributesArrayRef, stringRef);
          OS.CFRelease(stringRef);
        }
      }
      code = OS.noErr;
    }
    if (parent.accessible != null) {
      code =
          parent.accessible.internal_kEventAccessibleGetAllAttributeNames(
              nextHandler, theEvent, userData);
    }
    return code;
  }
}
