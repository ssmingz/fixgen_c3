class PlaceHold {
  void mouseDown(int id, int sel, int theEvent) {
    if (!parent.mouseEvent(parent.view.id, sel, theEvent, MouseDown)) {
      return;
    }
    Display display = this.display;
    display.trackingControl = parent;
    super.mouseDown(id, sel, theEvent);
    display.trackingControl = null;
    if (((style & SWT.DROP_DOWN) != 0) && (id == view.id)) {
      NSRect frame = view.frame();
      Event event = new Event();
      event.detail = SWT.ARROW;
      event.x = ((int) (frame.x));
      event.y = ((int) (frame.y + frame.height));
      postEvent(Selection, event);
    }
  }
}
