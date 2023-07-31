class PlaceHold {
  int trackingProc(int browser, int itemID, int property, int theRect, int startPt, int modifiers) {
    Widget widget = getWidget(browser);
    if (widget != null) {
      return widget.trackingProc(browser, itemID, property, theRect, startPt, modifiers);
    }
    return OS.noErr;
  }
}
