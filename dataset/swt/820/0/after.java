class PlaceHold {
  void accessibilitySetValue_forAttribute(int id, int sel, int arg0, int arg1) {
    if ((id == view.id) && (accessible != null)) {
      id value = new id(arg0);
      NSString attribute = new NSString(arg1);
      accessible.internal_accessibilitySetValue_forAttribute(value, attribute, CHILDID_SELF);
    } else {
      super.accessibilitySetValue_forAttribute(id, sel, arg0, arg1);
    }
  }
}
