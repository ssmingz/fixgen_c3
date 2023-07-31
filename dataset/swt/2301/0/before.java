class PlaceHold {
  void createHandle() {
    NSView widget = ((NSView) (new SWTView().alloc()));
    widget.initWithFrame(new NSRect());
    view = widget;
  }
}
