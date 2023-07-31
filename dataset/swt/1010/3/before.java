class PlaceHold {
  int drawProc(int handle, int damage) {
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.processPaint(damage);
  }
}
