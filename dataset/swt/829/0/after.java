class PlaceHold {
  int windowTimerProc(int handle, int id) {
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.timerProc(id);
  }
}
