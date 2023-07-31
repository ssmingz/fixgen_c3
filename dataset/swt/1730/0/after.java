class PlaceHold {
  boolean checkScroll(int hItem) {
    if (getDrawing()) {
      return false;
    }
    int hRoot = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_ROOT, 0);
    int hParent = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_PARENT, hItem);
    while ((hParent != hRoot) && (hParent != 0)) {
      hParent = OS.SendMessage(handle, TVM_GETNEXTITEM, TVGN_PARENT, hParent);
    }
    return hParent == 0;
  }
}
