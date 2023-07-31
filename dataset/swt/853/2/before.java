class PlaceHold {
  private int handleDataBrowserCompareCallback(int cHandle, int item1ID, int item2ID, int sortID) {
    Widget widget = WidgetTable.get(cHandle);
    if (widget instanceof List) {
      List list = ((List) (widget));
      return list.handleCompareCallback(item1ID, item2ID, sortID);
    }
    if (widget instanceof Tree2) {
      Tree2 tree = ((Tree2) (widget));
      return tree.handleCompareCallback(item1ID, item2ID, sortID);
    }
    return OS.kNoErr;
  }
}
