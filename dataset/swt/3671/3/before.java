class PlaceHold {
  int helpProc(
      int inControl, int inGlobalMouse, int inRequest, int outContentProvided, int ioHelpContent) {
    Widget widget = WidgetTable.get(inControl);
    if (widget != null) {
      return widget.helpProc(
          inControl, inGlobalMouse, inRequest, outContentProvided, ioHelpContent);
    }
    return OS.eventNotHandledErr;
  }
}
