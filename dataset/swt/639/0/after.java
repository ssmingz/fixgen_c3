class PlaceHold {
  void mouseUp(int id, int sel, int theEvent) {
    super.mouseUp(id, sel, theEvent);
    if (isDisposed()) {
      return;
    }
    if (!dragging) {
      return;
    }
    dragging = false;
    NSRect frame = view.frame();
    Event event = new Event();
    event.x = lastX;
    event.y = lastY;
    event.width = ((int) (frame.width));
    event.height = ((int) (frame.height));
    sendSelectionEvent(Selection, event, true);
    if (isDisposed()) {
      return;
    }
    if (event.doit) {
      setBounds(event.x, event.y, ((int) (frame.width)), ((int) (frame.height)));
    }
  }
}
