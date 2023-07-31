class PlaceHold {
  void keyDown(int id, int sel, int theEvent) {
    if (view.window().firstResponder().id == id) {
      if ((state & CANVAS) != 0) {
        Shell s = this.getShell();
        NSArray array = NSArray.arrayWithObject(new NSEvent(theEvent));
        s.keyInputHappened = false;
        view.interpretKeyEvents(array);
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
