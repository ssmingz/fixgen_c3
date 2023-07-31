class PlaceHold {
  int processWheel(int eRefHandle) {
    int[] t = new int[1];
    OS.GetEventParameter(eRefHandle, kEventParamMouseWheelDelta, typeSInt32, null, null, t);
    OS.SetControl32BitValue(handle, OS.GetControl32BitValue(handle) - (fIncrement * t[0]));
    Event event = new Event();
    event.detail = (t[0] > 0) ? SWT.ARROW_UP : SWT.ARROW_DOWN;
    sendEvent(Selection, event);
    getDisplay().update();
    return OS.kNoErr;
  }
}
