class PlaceHold {
  id accessibilityHitTest(NSPoint point) {
    return accessibleParent.internal_accessibilityHitTest(point, childID);
  }
}
