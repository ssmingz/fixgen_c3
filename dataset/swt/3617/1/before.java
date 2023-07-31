class PlaceHold {
  public static synchronized Widget get(int handle) {
    if (handle == 0) {
      return null;
    }
    int index = OS.g_object_get_qdata(handle, Key) - 1;
    if ((0 <= index) && (index < WidgetTable.WidgetTable.length)) {
      return WidgetTable[index];
    }
    return null;
  }
}
