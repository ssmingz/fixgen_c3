class PlaceHold {
  int processFocusIn() {
    super.processFocusIn();
    if (handle == 0) {
      return 0;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      return 0;
    }
    drawFrame(null);
    OS.TXNFocus(fTX, true);
    if ((style & SWT.MULTI) != 0) {
      return 0;
    }
    return 0;
  }
}
