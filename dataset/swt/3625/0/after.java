class PlaceHold {
  int processFocusIn() {
    super.processFocusIn();
    if (handle == 0) {
      return 0;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      return 0;
    }
    OS.TXNFocus(fTX, true);
    drawFrame(null);
    if ((style & SWT.MULTI) != 0) {
      return 0;
    }
    return 0;
  }
}
