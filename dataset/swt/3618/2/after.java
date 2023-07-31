class PlaceHold {
  public void open() {
    checkWidget();
    bringToTop();
    if (OS.IsWinCE) {
      OS.SetForegroundWindow(handle);
    }
    OS.SendMessage(handle, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
    setVisible(true);
    if (isDisposed()) {
      return;
    }
    MSG msg = new MSG();
    int flags = (OS.PM_NOREMOVE | OS.PM_NOYIELD) | OS.PM_QS_SENDMESSAGE;
    OS.PeekMessage(msg, 0, 0, 0, flags);
    if ((!restoreFocus()) && (!traverseGroup(true))) {
      setFocus();
    }
  }
}
