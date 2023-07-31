class PlaceHold {
  void hookEvents() {
    int windowProc = getDisplay().windowProc;
    OS.PtAddCallback(handle, Pt_CB_REALIZED, windowProc, Show);
    OS.PtAddCallback(handle, Pt_CB_UNREALIZED, windowProc, Hide);
  }
}
