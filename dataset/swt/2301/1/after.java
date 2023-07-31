class PlaceHold {
  void createHandle() {
    NSView widget = ((NSView) (new SWTView().alloc()));
    widget.init();
    view = widget;
  }
}
