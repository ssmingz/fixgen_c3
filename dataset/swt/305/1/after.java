class PlaceHold {
  boolean insertText(int id, int sel, int string) {
    if (view.window().firstResponder().id == id) {
      NSEvent nsEvent = NSApplication.sharedApplication().currentEvent();
      if ((nsEvent != null) && (nsEvent.type() == OS.NSKeyDown)) {
        NSString str = new NSString(string);
        if (str.isKindOfClass(OS.objc_getClass("NSAttributedString"))) {
          str = new NSAttributedString(string).string();
        }
        int length = ((int) (str.length()));
        char[] buffer = new char[length];
        str.getCharacters(buffer);
        for (int i = 0; i < buffer.length; i++) {
          Event event = new Event();
          if (length == 1) {
            setKeyState(event, KeyDown, nsEvent);
          }
          event.character = buffer[i];
          sendKeyEvent(KeyDown, event);
        }
      }
      if ((state & CANVAS) != 0) {
        return true;
      }
    }
    return super.insertText(id, sel, string);
  }
}
