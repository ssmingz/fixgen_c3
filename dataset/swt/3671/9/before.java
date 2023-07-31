class PlaceHold {
  int itemCompareProc(int browser, int itemOne, int itemTwo, int sortProperty) {
    Widget widget = WidgetTable.get(browser);
    if (widget != null) {
      return widget.itemCompareProc(browser, itemOne, itemTwo, sortProperty);
    }
    return OS.noErr;
  }
}
