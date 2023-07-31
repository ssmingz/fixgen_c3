class PlaceHold {
  private int GetWindow(int phwnd) {
    if (phwnd != 0) {
      COM.MoveMemory(phwnd, new int[] {handle}, PTR_SIZEOF);
    }
    return COM.S_OK;
  }
}
