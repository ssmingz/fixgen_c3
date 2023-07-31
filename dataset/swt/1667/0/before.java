class PlaceHold {
  int windowTimerProc(int handle) {
    Widget widget = getWidget(handle);
    if (widget == null) {
      return 0;
    }
    return widget.timerProc(handle);
  }
}
