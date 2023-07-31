class PlaceHold {
  void doCommandBySelector(int id, int sel, int selector) {
    if (view.window().firstResponder().id == id) {
      if (imeInComposition()) {
        return;
      }
      Shell s = this.getShell();
      NSEvent nsEvent = NSApplication.sharedApplication().currentEvent();
      if ((nsEvent != null) && (nsEvent.type() == OS.NSKeyDown)) {
        int modifiers = nsEvent.modifierFlags();
        if ((s.keyInputHappened == false) || ((modifiers & OS.NSCommandKeyMask) != 0)) {
          s.keyInputHappened = true;
          boolean[] consume = new boolean[1];
          if (translateTraversal(nsEvent.keyCode(), nsEvent, consume)) {
            return;
          }
          if (isDisposed()) {
            return;
          }
          if (!sendKeyEvent(nsEvent, KeyDown)) {
            return;
          }
          if (consume[0]) {
            return;
          }
        }
      }
      if ((state & CANVAS) != 0) {
        return;
      }
    }
    super.doCommandBySelector(id, sel, selector);
  }
}
