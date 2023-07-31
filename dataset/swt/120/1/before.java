class PlaceHold {
  int controlProc(int nextHandler, int theEvent, int userData) {
    int eventKind = OS.GetEventKind(theEvent);
    switch (eventKind) {
      case OS.kEventControlActivate:
        return kEventControlActivate(nextHandler, theEvent, userData);
      case OS.kEventControlApplyBackground:
        return kEventControlApplyBackground(nextHandler, theEvent, userData);
      case OS.kEventControlBoundsChanged:
        return kEventControlBoundsChanged(nextHandler, theEvent, userData);
      case OS.kEventControlClick:
        return kEventControlClick(nextHandler, theEvent, userData);
      case OS.kEventControlContextualMenuClick:
        return kEventControlContextualMenuClick(nextHandler, theEvent, userData);
      case OS.kEventControlDeactivate:
        return kEventControlDeactivate(nextHandler, theEvent, userData);
      case OS.kEventControlDraw:
        return kEventControlDraw(nextHandler, theEvent, userData);
      case OS.kEventControlHit:
        return kEventControlHit(nextHandler, theEvent, userData);
      case OS.kEventControlSetCursor:
        return kEventControlSetCursor(nextHandler, theEvent, userData);
      case OS.kEventControlSetFocusPart:
        return kEventControlSetFocusPart(nextHandler, theEvent, userData);
      case OS.kEventControlTrack:
        return kEventControlTrack(nextHandler, theEvent, userData);
      case OS.kEventControlGetFocusPart:
        return kEventControlGetFocusPart(nextHandler, theEvent, userData);
    }
    return OS.eventNotHandledErr;
  }
}
