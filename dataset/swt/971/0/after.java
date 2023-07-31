class PlaceHold {
  boolean sendIMKeyEvent(int type, XKeyEvent xEvent, int textHandle) {
    byte[] buffer = new byte[512];
    int[] status = new int[1];
    int[] unused = new int[1];
    int focusHandle = OS.XtWindowToWidget(xEvent.display, xEvent.window);
    int length = OS.XmImMbLookupString(focusHandle, xEvent, buffer, buffer.length, unused, status);
    if (status[0] == OS.XBufferOverflow) {
      buffer = new byte[length];
      length = OS.XmImMbLookupString(focusHandle, xEvent, buffer, length, unused, status);
    }
    if (length == 0) {
      return true;
    }
    char[] chars = Converter.mbcsToWcs(null, buffer);
    int index = 0;
    int count = 0;
    while (index < chars.length) {
      if (chars[index] == 0) {
        chars[count] = 0;
        break;
      }
      Event event = new Event();
      event.time = xEvent.time;
      event.character = chars[index];
      setInputState(event, xEvent);
      sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
      if (event.doit) {
        chars[count++] = chars[index];
      }
      index++;
    }
    if (count == 0) {
      return false;
    }
    if (textHandle != 0) {
      byte[] testBuffer = new byte[5];
      int testLength =
          OS.XmImMbLookupString(textHandle, xEvent, testBuffer, testBuffer.length, unused, unused);
      if ((testLength == 0) || (index != count)) {
        int[] start = new int[1];
        int[] end = new int[1];
        OS.XmTextGetSelectionPosition(textHandle, start, end);
        if (start[0] == end[0]) {
          start[0] = end[0] = OS.XmTextGetInsertionPosition(textHandle);
        }
        boolean warnings = display.getWarnings();
        display.setWarnings(false);
        if (index != count) {
          buffer = Converter.wcsToMbcs(getCodePage(), chars, true);
        }
        OS.XmTextReplace(textHandle, start[0], end[0], buffer);
        int position = start[0] + count;
        OS.XmTextSetInsertionPosition(textHandle, position);
        display.setWarnings(warnings);
        return false;
      }
    }
    return true;
  }
}
