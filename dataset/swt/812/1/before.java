class PlaceHold {
  int kEventControlActivate(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlActivate(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    OS.TXNFocus(txnObject, hasFocus());
    OS.TXNActivate(txnObject, txnFrameID, kScrollBarsSyncAlwaysActive);
    return result;
  }
}
