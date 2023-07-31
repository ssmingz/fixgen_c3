class PlaceHold {
  void onFocusIn(Event e) {
    if (inDispose) {
      return;
    }
    if (state != STATE_UIACTIVE) {
      doVerb(OLEIVERB_SHOW);
    }
    if (objIOleInPlaceObject == null) {
      return;
    }
    if (isFocusControl()) {
      return;
    }
    int[] phwnd = new int[1];
    objIOleInPlaceObject.GetWindow(phwnd);
    if (phwnd[0] == 0) {
      return;
    }
    OS.SetFocus(phwnd[0]);
  }
}
