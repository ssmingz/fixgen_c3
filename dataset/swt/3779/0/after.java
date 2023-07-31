class PlaceHold {
  int processFocusIn() {
    sendEvent(FocusIn);
    if (handle == 0) {
      return 0;
    }
    processIMEFocusIn();
    return 0;
  }
}
