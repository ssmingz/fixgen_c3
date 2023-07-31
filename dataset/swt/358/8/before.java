class PlaceHold {
  void setFocusToChild(int childId) {
    updateChildren();
    AccessibleObject accObject = getChildByID(childId);
    if (accObject != null) {
      OS.atk_focus_tracker_notify(accObject.handle);
    }
  }
}
