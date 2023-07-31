class PlaceHold {
  Widget removeWidget(int handle) {
    if (handle == 0) {
      return null;
    }
    Widget widget = null;
    int index = OS.g_object_get_qdata(handle, SWT_OBJECT_INDEX) - 1;
    if ((0 <= index) && (index < widgetTable.length)) {
      widget = widgetTable[index];
      widgetTable[index] = null;
      indexTable[index] = freeSlot;
      freeSlot = index;
      OS.g_object_set_qdata(handle, SWT_OBJECT_INDEX, 0);
    }
    return widget;
  }
}
