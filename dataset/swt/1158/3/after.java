class PlaceHold {
  void createWidget() {
    super.createWidget();
    items = new ExpandItem[4];
    focusIndex = -1;
    if (!isAppThemed()) {
      backgroundMode = SWT.INHERIT_DEFAULT;
    }
  }
}
