class PlaceHold {
  int actionProc(int theControl, int partCode) {
    Event event = new Event();
    int value = OS.GetControl32BitValue(handle);
    switch (partCode) {
      case OS.kControlUpButtonPart:
        value -= increment;
        event.detail = SWT.ARROW_UP;
        break;
      case OS.kControlPageUpPart:
        value -= pageIncrement;
        event.detail = SWT.PAGE_UP;
        break;
      case OS.kControlPageDownPart:
        value += pageIncrement;
        event.detail = SWT.PAGE_DOWN;
        break;
      case OS.kControlDownButtonPart:
        value += increment;
        event.detail = SWT.ARROW_DOWN;
        break;
      case OS.kControlIndicatorPart:
        dragging = true;
        event.detail = SWT.DRAG;
        break;
      default:
        return 0;
    }
    OS.SetControl32BitValue(handle, value);
    sendEvent(Selection, event);
    if (!OS.HIVIEW) {
      parent.update(true);
    }
    return 0;
  }
}
