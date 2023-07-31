class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    int windowProc = getDisplay().windowProc;
    OS.PtAddCallback(handle, Pt_CB_PG_PANEL_SWITCHING, windowProc, Selection);
  }
}
