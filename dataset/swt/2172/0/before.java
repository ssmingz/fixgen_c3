class PlaceHold {
  int controlProc(int nextHandler, int theEvent, int userData) {
    Widget widget = WidgetTable.get(userData);
    if (widget != null) {
      return widget.controlProc(nextHandler, theEvent, userData);
    }
    return OS.eventNotHandledErr;
  }
}
