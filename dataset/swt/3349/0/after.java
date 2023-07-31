class PlaceHold {
  public void setWidth(int width) {
    checkWidget();
    OS.SetDataBrowserTableViewNamedColumnWidth(
        parent.handle, id, ((short) (width + Table.EXTRA_WIDTH)));
    updateHeader();
  }
}
