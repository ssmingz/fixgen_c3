class PlaceHold {
  int osToChildID(int osChildID) {
    if (osChildID == COM.CHILDID_SELF) {
      return ACC.CHILDID_SELF;
    }
    if (!(control instanceof Tree)) {
      return osChildID - 1;
    }
    if (OS.COMCTL32_MAJOR < 6) {
      return osChildID;
    }
    return OS.SendMessage(control.handle, TVM_MAPACCIDTOHTREEITEM, osChildID, 0);
  }
}
