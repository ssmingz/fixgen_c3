class PlaceHold {
  void createHandle() {
    NSTabView widget = ((NSTabView) (new SWTTabView().alloc()));
    widget.init();
    widget.setDelegate(widget);
    if ((style & SWT.BOTTOM) != 0) {
      widget.setTabViewType(NSBottomTabsBezelBorder);
    }
    view = widget;
  }
}
