class PlaceHold {
  boolean accessibilityIsAttributeSettable(int id, int sel, int arg0) {
    boolean returnValue = false;
    if ((id == view.id) && (accessible != null)) {
      NSString attribute = new NSString(arg0);
      returnValue = accessible.internal_accessibilityIsAttributeSettable(attribute, CHILDID_SELF);
    }
    if (!returnValue) {
      returnValue = super.accessibilityIsAttributeSettable(id, sel, arg0);
    }
    return returnValue;
  }
}
