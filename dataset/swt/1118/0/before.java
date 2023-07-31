class PlaceHold {
  void createHandle() {
    if ((style & SWT.READ_ONLY) != 0) {
      NSPopUpButton widget = ((NSPopUpButton) (new SWTPopUpButton().alloc()));
      widget.initWithFrame(new NSRect(), false);
      widget.menu().setAutoenablesItems(false);
      widget.setTarget(widget);
      widget.setAction(sel_sendSelection);
      view = widget;
    } else {
      NSComboBox widget = ((NSComboBox) (new SWTComboBox().alloc()));
      widget.init();
      widget.setDelegate(widget);
      view = widget;
    }
  }
}
