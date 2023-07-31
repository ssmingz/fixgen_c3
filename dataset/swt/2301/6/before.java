class PlaceHold {
  void createHandle() {
    NSProgressIndicator widget = ((NSProgressIndicator) (new SWTProgressIndicator().alloc()));
    widget.initWithFrame(new NSRect());
    widget.setUsesThreadedAnimation(false);
    widget.setIndeterminate((style & SWT.INDETERMINATE) != 0);
    if ((style & SWT.INDETERMINATE) != 0) {
      widget.startAnimation(null);
    }
    view = widget;
  }
}
