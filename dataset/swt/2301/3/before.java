class PlaceHold {
  void createHandle() {
    NSTabView widget = ((NSTabView) (new SWTTabView().alloc()));
    widget.initWithFrame(new NSRect());
    widget.setDelegate(widget);
    if ((style & SWT.BOTTOM) != 0) {
      widget.setTabViewType(NSBottomTabsBezelBorder);
    }
    view = widget;
  }
}
