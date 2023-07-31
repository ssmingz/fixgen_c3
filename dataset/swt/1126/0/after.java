class PlaceHold {
  int launcherProc(int nextHandler, int theEvent, int userData) {
    int[] stringRef = new int[1];
    OS.GetEventParameter(theEvent, SWT_OPEN_FILE_PARAM, typeCFStringRef, null, 4, null, stringRef);
    int length = 0;
    if (stringRef[0] != 0) {
      length = OS.CFStringGetLength(stringRef[0]);
    }
    char[] buffer = new char[length];
    if (length > 0) {
      CFRange range = new CFRange();
      range.length = length;
      OS.CFStringGetCharacters(stringRef[0], range, buffer);
    }
    String filePath = new String(buffer);
    Event event = new Event();
    event.text = filePath;
    sendEvent(OpenDocument, event);
    return OS.noErr;
  }
}
