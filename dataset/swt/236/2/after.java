class PlaceHold {
  void createWidget() {
    super.createWidget();
    items = new TableItem[4];
    columns = new TableColumn[4];
    showIndex = -1;
    if (OS.VERSION >= 0x1050) {
      OS.DataBrowserChangeAttributes(handle, kDataBrowserAttributeAutoHideScrollBars, 0);
    }
  }
}
