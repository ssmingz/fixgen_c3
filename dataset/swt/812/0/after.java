class PlaceHold {
  int kEventControlDeactivate(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlDeactivate(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if (!OS.HIVIEW) {
      OS.TXNFocus(txnObject, hasFocus());
      OS.TXNActivate(txnObject, txnFrameID, kScrollBarsSyncWithFocus);
    }
    return result;
  }
}
