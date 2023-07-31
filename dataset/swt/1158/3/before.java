class PlaceHold {
  void createWidget() {
    super.createWidget();
    items = new ExpandItem[4];
    focusIndex = -1;
    if ((OS.COMCTL32_MAJOR < 6) || (!OS.IsAppThemed())) {
      backgroundMode = SWT.INHERIT_DEFAULT;
    }
  }
}
