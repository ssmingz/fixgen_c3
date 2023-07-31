class PlaceHold {
  int processFocusOut() {
    sendEvent(FocusOut);
    if (handle == 0) {
      return 0;
    }
    processIMEFocusOut();
    return 0;
  }
}
