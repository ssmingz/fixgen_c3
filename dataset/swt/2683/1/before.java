class PlaceHold {
  int osToState(int osState) {
    int state = ACC.STATE_NORMAL;
    if ((osState & COM.STATE_SYSTEM_SELECTED) != 0) {
      state |= ACC.STATE_SELECTED;
    }
    if ((osState & COM.STATE_SYSTEM_SELECTABLE) != 0) {
      state |= ACC.STATE_SELECTABLE;
    }
    if ((osState & COM.STATE_SYSTEM_MULTISELECTABLE) != 0) {
      state |= ACC.STATE_MULTISELECTABLE;
    }
    if ((osState & COM.STATE_SYSTEM_FOCUSED) != 0) {
      state |= ACC.STATE_FOCUSED;
    }
    if ((osState & COM.STATE_SYSTEM_FOCUSABLE) != 0) {
      state |= ACC.STATE_FOCUSABLE;
    }
    if ((osState & COM.STATE_SYSTEM_PRESSED) != 0) {
      state |= ACC.STATE_PRESSED;
    }
    if ((osState & COM.STATE_SYSTEM_CHECKED) != 0) {
      state |= ACC.STATE_CHECKED;
    }
    if ((osState & COM.STATE_SYSTEM_EXPANDED) != 0) {
      state |= ACC.STATE_EXPANDED;
    }
    if ((osState & COM.STATE_SYSTEM_COLLAPSED) != 0) {
      state |= ACC.STATE_COLLAPSED;
    }
    if ((osState & COM.STATE_SYSTEM_HOTTRACKED) != 0) {
      state |= ACC.STATE_HOTTRACKED;
    }
    if ((osState & COM.STATE_SYSTEM_BUSY) != 0) {
      state |= ACC.STATE_BUSY;
    }
    if ((osState & COM.STATE_SYSTEM_READONLY) != 0) {
      state |= ACC.STATE_READONLY;
    }
    if ((osState & COM.STATE_SYSTEM_INVISIBLE) != 0) {
      state |= ACC.STATE_INVISIBLE;
    }
    if ((osState & COM.STATE_SYSTEM_OFFSCREEN) != 0) {
      state |= ACC.STATE_OFFSCREEN;
    }
    if ((osState & COM.STATE_SYSTEM_SIZEABLE) != 0) {
      state |= ACC.STATE_SIZEABLE;
    }
    return state;
  }
}
