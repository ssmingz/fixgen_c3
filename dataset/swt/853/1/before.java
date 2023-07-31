class PlaceHold {
  private int handleDataBrowserItemNotificationCallback(int cHandle, int item, int message) {
    Widget widget = WidgetTable.get(cHandle);
    if (widget instanceof List) {
      List list = ((List) (widget));
      return list.handleItemNotificationCallback(item, message);
    }
    if (widget instanceof Tree2) {
      Tree2 tree = ((Tree2) (widget));
      return tree.handleItemNotificationCallback(item, message);
    }
    return OS.kNoErr;
  }
}
