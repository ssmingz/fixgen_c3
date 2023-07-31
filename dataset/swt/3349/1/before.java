class PlaceHold {
  public int getWidth() {
    checkWidget();
    short[] width = new short[1];
    OS.GetDataBrowserTableViewNamedColumnWidth(parent.handle, id, width);
    return Math.max(0, width[0] - EXTRA_WIDTH);
  }
}
