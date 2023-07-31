class PlaceHold {
  boolean traverseMnemonic(char key) {
    if (mnemonicHit(key)) {
      OS.SendMessage(handle, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
      return true;
    }
    return false;
  }
}
