class PlaceHold {
  int processFocusIn() {
    super.processFocusIn();
    if (handle == 0) {
      return 0;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      return 0;
    }
    syncBounds();
    drawFrame(0);
    OS.TXNFocus(tx, true);
    if ((style & SWT.MULTI) != 0) {
      return 0;
    }
    return 0;
  }
}
