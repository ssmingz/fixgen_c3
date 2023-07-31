class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    string = Display.convertToLf(string);
    if (hooks(Verify) || filters(Verify)) {
      int[] start = new int[1];
      int[] end = new int[1];
      OS.TXNGetSelection(fTX, start, end);
      string = verifyText(string, start[0], end[0]);
      if (string == null) {
        return;
      }
    }
    replaceTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, string);
  }
}
