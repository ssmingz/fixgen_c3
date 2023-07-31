class PlaceHold {
  void accessibilityPerformAction(NSString action) {
    accessibleParent.internal_accessibilityPerformAction(action, childID);
  }
}
