class PlaceHold {
  public void open() {
    checkWidget();
    bringToTop();
    if (OS.IsWinCE) {
      OS.SetForegroundWindow(handle);
    }
    OS.SendMessage(handle, WM_CHANGEUISTATE, UIS_INITIALIZE, 0);
    setVisible(true);
    MSG msg = new MSG();
    OS.PeekMessage(msg, 0, 0, 0, OS.PM_NOREMOVE | OS.PM_NOYIELD);
    if (!restoreFocus()) {
      traverseGroup(true);
    }
  }
}
