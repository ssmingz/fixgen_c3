class PlaceHold {
  void setFrameOrigin(int id, int sel, NSPoint point) {
    NSView topView = topView();
    if (topView.id != id) {
      super.setFrameOrigin(id, sel, point);
      return;
    }
    NSRect frame = topView.frame();
    super.setFrameOrigin(id, sel, point);
    if ((frame.x != point.x) || (frame.y != point.y)) {
      moved();
    }
  }
}
