class PlaceHold {
  int actionProc(int theControl, int partCode) {
    Widget widget = WidgetTable.get(theControl);
    if (widget != null) {
      return widget.actionProc(theControl, partCode);
    }
    return OS.noErr;
  }
}
