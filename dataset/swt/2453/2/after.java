class PlaceHold {
  void keyDown(int id, int sel, int theEvent) {
    if (view.window().firstResponder().id == id) {
      Shell s = this.getShell();
      s.keyInputHappened = false;
      boolean textInput =
          OS.objc_msgSend(id, sel_conformsToProtocol_, OS.objc_getProtocol("NSTextInput")) != 0;
      if (!textInput) {
        NSEvent nsEvent = new NSEvent(theEvent);
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
      } else {
        super.keyDown(id, sel, theEvent);
        if (imeInComposition()) {
          return;
        }
        if (!s.keyInputHappened) {
          NSEvent nsEvent = new NSEvent(theEvent);
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
        return;
      }
    }
    super.keyDown(id, sel, theEvent);
  }
}
