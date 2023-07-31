class PlaceHold {
  int processFocusIn() {
    super.processFocusIn();
    if (handle == 0) {
      return 0;
    }
    if (fTX != 0) {
      OS.TXNFocus(fTX, true);
      drawFrame();
    }
    return 0;
  }
}
