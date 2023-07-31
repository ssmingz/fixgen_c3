class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    parent.ignoreExpand = true;
    if (expanded) {
      OS.OpenDataBrowserContainer(parent.handle, id);
    } else {
      OS.CloseDataBrowserContainer(parent.handle, id);
    }
    parent.ignoreExpand = false;
    cached = true;
    parent.setScrollWidth(true);
    parent.fixScrollBar();
  }
}
