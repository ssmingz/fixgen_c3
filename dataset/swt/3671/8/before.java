class PlaceHold {
  int windowTimerProc(int handle) {
    Widget widget = WidgetTable.get(handle);
    if (widget == null) {
      return 0;
    }
    return widget.timerProc(handle);
  }
}
