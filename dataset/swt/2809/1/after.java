class PlaceHold {
  Widget getWidget(int handle) {
    if (handle == 0) {
      return null;
    }
    int index = OS.g_object_get_qdata(handle, SWT_OBJECT_INDEX) - 1;
    if ((0 <= index) && (index < widgetTable.length)) {
      return widgetTable[((int) (index))];
    }
    return null;
  }
}
