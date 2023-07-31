class PlaceHold {
  int itemDataProc(int browser, int item, int property, int itemData, int setValue) {
    Widget widget = getWidget(browser);
    if (widget != null) {
      return widget.itemDataProc(browser, item, property, itemData, setValue);
    }
    return OS.noErr;
  }
}
