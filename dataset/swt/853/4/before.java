class PlaceHold {
  private int handleDataBrowserDataCallback(
      int cHandle, int item, int property, int itemData, int setValue) {
    Widget widget = WidgetTable.get(cHandle);
    if (widget instanceof List) {
      List list = ((List) (widget));
      return list.handleItemCallback(item, property, itemData);
    }
    if (widget instanceof Tree2) {
      Tree2 tree = ((Tree2) (widget));
      return tree.handleItemCallback(item, property, itemData, setValue);
    }
    return OS.kNoErr;
  }
}
