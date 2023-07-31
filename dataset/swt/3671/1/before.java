class PlaceHold {
  int windowProc(int handle, int arg0, int arg1, int arg2, int user_data) {
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.windowProc(handle, arg0, arg1, arg2, user_data);
  }
}
