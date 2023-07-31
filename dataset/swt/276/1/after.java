class PlaceHold {
  int processFocusOut() {
    super.processFocusOut();
    if (handle == 0) {
      return 0;
    }
    if ((style & SWT.READ_ONLY) != 0) {
      return 0;
    }
    syncBounds();
    OS.TXNFocus(tx, false);
    drawFrame(0);
    if ((style & SWT.MULTI) != 0) {
      return 0;
    }
    return 0;
  }
}
