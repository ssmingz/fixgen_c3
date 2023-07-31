class PlaceHold {
  int windowProc(int nextHandler, int theEvent, int userData) {
    int eventKind = OS.GetEventKind(theEvent);
    switch (eventKind) {
      case OS.kEventWindowActivated:
        return kEventWindowActivated(nextHandler, theEvent, userData);
      case OS.kEventWindowBoundsChanged:
        return kEventWindowBoundsChanged(nextHandler, theEvent, userData);
      case OS.kEventWindowClose:
        return kEventWindowClose(nextHandler, theEvent, userData);
      case OS.kEventWindowCollapsed:
        return kEventWindowCollapsed(nextHandler, theEvent, userData);
      case OS.kEventWindowCollapsing:
        return kEventWindowCollapsing(nextHandler, theEvent, userData);
      case OS.kEventWindowDeactivated:
        return kEventWindowDeactivated(nextHandler, theEvent, userData);
      case OS.kEventWindowDrawContent:
        return kEventWindowDrawContent(nextHandler, theEvent, userData);
      case OS.kEventWindowExpanded:
        return kEventWindowExpanded(nextHandler, theEvent, userData);
      case OS.kEventWindowGetRegion:
        return kEventWindowGetRegion(nextHandler, theEvent, userData);
      case OS.kEventWindowHidden:
        return kEventWindowHidden(nextHandler, theEvent, userData);
      case OS.kEventWindowHitTest:
        return kEventWindowHitTest(nextHandler, theEvent, userData);
      case OS.kEventWindowShown:
        return kEventWindowShown(nextHandler, theEvent, userData);
      case OS.kEventWindowUpdate:
        return kEventWindowUpdate(nextHandler, theEvent, userData);
      case OS.kEventWindowGetClickModality:
        return kEventWindowGetClickModality(nextHandler, theEvent, userData);
    }
    return OS.eventNotHandledErr;
  }
}
