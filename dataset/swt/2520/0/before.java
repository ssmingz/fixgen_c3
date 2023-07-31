class PlaceHold {
  private int OnUIActivate() {
    state = STATE_UIACTIVE;
    int[] phwnd = new int[1];
    if (objIOleInPlaceObject.GetWindow(phwnd) == COM.S_OK) {
      OS.SetWindowPos(phwnd[0], HWND_TOP, 0, 0, 0, 0, OS.SWP_NOSIZE | OS.SWP_NOMOVE);
    }
    return COM.S_OK;
  }
}
