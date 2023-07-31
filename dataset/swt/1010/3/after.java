class PlaceHold {
  int drawProc(int handle, int damage) {
    if (thread != Thread.currentThread()) {
      return 0;
    }
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.processPaint(damage);
  }
}
