class PlaceHold {
  void setFrameSize(int id, int sel, NSSize size) {
    NSView topView = topView();
    if (topView.id != id) {
      super.setFrameSize(id, sel, size);
      return;
    }
    NSRect frame = topView.frame();
    super.setFrameSize(id, sel, size);
    if ((frame.width != size.width) || (frame.height != size.height)) {
      resized();
    }
  }
}
