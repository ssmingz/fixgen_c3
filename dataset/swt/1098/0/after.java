class PlaceHold {
  int actionProc(int theControl, int partCode) {
    int result = super.actionProc(theControl, partCode);
    if (result == OS.noErr) {
      return result;
    }
    sendSelectionEvent(Selection, null, true);
    return result;
  }
}
