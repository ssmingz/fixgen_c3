class PlaceHold {
  boolean insertText(int id, int sel, int string) {
    if (view.window().firstResponder().id == id) {
      Display display = this.display;
      NSEvent nsEvent = NSApplication.sharedApplication().currentEvent();
      if (((!display.keyInputHappened) && (nsEvent != null)) && (nsEvent.type() == OS.NSKeyDown)) {
        NSString str = new NSString(string);
        if (str.isKindOfClass(OS.objc_getClass("NSAttributedString"))) {
          str = new NSAttributedString(string).string();
        }
        int length = ((int) (str.length()));
        char[] buffer = new char[length];
        str.getCharacters(buffer);
        for (int i = 0; i < buffer.length; i++) {
          display.keyInputHappened = true;
          Event event = new Event();
          if (i == 0) {
            setKeyState(event, KeyDown, nsEvent);
          }
          event.character = buffer[i];
          if (!sendKeyEvent(KeyDown, event)) {
            return false;
          }
        }
      }
      if ((state & CANVAS) != 0) {
        return true;
      }
    }
    return super.insertText(id, sel, string);
  }
}
