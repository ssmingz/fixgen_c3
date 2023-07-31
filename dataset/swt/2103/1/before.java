class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (OS.IsWinCE) {
      OS.MsgWaitForMultipleObjectsEx(0, 0, INFINITE, QS_ALLINPUT, MWMO_INPUTAVAILABLE);
      return true;
    }
    return OS.WaitMessage();
  }
}
