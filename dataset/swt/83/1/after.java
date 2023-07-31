class PlaceHold {
  void createWidget() {
    super.createWidget();
    items = new TreeItem[4];
    columns = new TreeColumn[4];
    if (OS.VERSION >= 0x1050) {
      OS.DataBrowserChangeAttributes(handle, kDataBrowserAttributeAutoHideScrollBars, 0);
    }
  }
}
