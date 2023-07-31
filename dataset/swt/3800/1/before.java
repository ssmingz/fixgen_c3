class PlaceHold {
  int childIDToOs(int childID) {
    if (childID == ACC.CHILDID_SELF) {
      return COM.CHILDID_SELF;
    }
    if (!(control instanceof Tree)) {
      return childID + 1;
    }
    if (OS.COMCTL32_MAJOR < 6) {
      return childID;
    }
    return OS.SendMessage(control.handle, TVM_MAPHTREEITEMTOACCID, childID, 0);
  }
}
