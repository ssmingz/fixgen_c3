class PlaceHold {
  public Widget findWidget(int handle) {
    checkDevice();
    return WidgetTable.get(handle);
  }
}
