class PlaceHold {
  void setFocusToChild(int childId) {
    updateChildren();
    AccessibleObject accObject = getChildByID(childId);
    if (accObject != null) {
      ATK.atk_focus_tracker_notify(accObject.handle);
    }
  }
}
