class PlaceHold {
  void onFocusIn(Event e) {
    String progID = getProgramID();
    if (progID == null) {
      return;
    }
    if (!progID.startsWith(SHELL_PROG_ID)) {
      super.onFocusIn(e);
      return;
    }
    if (objIOleInPlaceObject == null) {
      return;
    }
    if (!isActivated) {
      doVerb(OLEIVERB_UIACTIVATE);
    }
    if (isFocusControl()) {
      return;
    }
    long[] phwnd = new long[1];
    objIOleInPlaceObject.GetWindow(phwnd);
    if (phwnd[0] == 0) {
      return;
    }
    OS.SetFocus(phwnd[0]);
  }
}
