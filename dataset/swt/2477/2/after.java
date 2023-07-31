class PlaceHold {
  int accessibilityHitTest(int id, int sel, NSPoint point) {
    id returnValue = null;
    if ((id == accessibleHandle()) && (accessible != null)) {
      returnValue = accessible.internal_accessibilityHitTest(point, CHILDID_SELF);
    }
    if (returnValue == null) {
      return super.accessibilityHitTest(id, sel, point);
    } else {
      return returnValue.id;
    }
  }
}
