class PlaceHold {
  int accessibilityHitTest(int id, int sel, NSPoint point) {
    id returnValue = null;
    if ((id == view.id)
        || (((view instanceof NSControl) && (((NSControl) (view)).cell() != null))
            && (((NSControl) (view)).cell().id == id))) {
      if (accessible != null) {
        returnValue = accessible.internal_accessibilityHitTest(point, CHILDID_SELF);
      }
    }
    if (returnValue == null) {
      return super.accessibilityHitTest(id, sel, point);
    } else {
      return returnValue.id;
    }
  }
}
