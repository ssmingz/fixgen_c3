class PlaceHold {
  LRESULT wmMeasureChild(int wParam, int lParam) {
    MEASUREITEMSTRUCT struct = new MEASUREITEMSTRUCT();
    OS.MoveMemory(struct, lParam, sizeof);
    if (itemHeight == (-1)) {
      int empty = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 0, 0);
      int oneItem = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 1, 0);
      struct.itemHeight = OS.HIWORD(oneItem) - OS.HIWORD(empty);
    } else {
      struct.itemHeight = itemHeight;
    }
    OS.MoveMemory(lParam, struct, sizeof);
    return null;
  }
}
