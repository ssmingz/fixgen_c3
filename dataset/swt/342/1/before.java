class PlaceHold {
  int actionProc(int theControl, int partCode) {
    int result = super.actionProc(theControl, partCode);
    if (result == OS.noErr) {
      return result;
    }
    Event event = new Event();
    int inc = 0;
    switch (partCode) {
      case OS.kControlUpButtonPart:
        inc -= increment;
        event.detail = SWT.ARROW_UP;
        break;
      case OS.kControlPageUpPart:
        inc -= pageIncrement;
        event.detail = SWT.PAGE_UP;
        break;
      case OS.kControlPageDownPart:
        inc += pageIncrement;
        event.detail = SWT.PAGE_DOWN;
        break;
      case OS.kControlDownButtonPart:
        inc += increment;
        event.detail = SWT.ARROW_DOWN;
        break;
      case OS.kControlIndicatorPart:
        dragging = true;
        event.detail = SWT.DRAG;
        break;
      default:
        return result;
    }
    if (oldActionProc != 0) {
      OS.Call(oldActionProc, theControl, partCode);
      parent.redrawBackgroundImage();
    } else {
      int value = OS.GetControl32BitValue(handle) + inc;
      OS.SetControl32BitValue(handle, value);
    }
    sendEvent(Selection, event);
    return result;
  }
}
