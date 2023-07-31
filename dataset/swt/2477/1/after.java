class PlaceHold {
  int accessibilityParameterizedAttributeNames(int id, int sel) {
    if ((id == accessibleHandle()) && (accessible != null)) {
      NSArray returnValue =
          accessible.internal_accessibilityParameterizedAttributeNames(CHILDID_SELF);
      if (returnValue != null) {
        return returnValue.id;
      }
    }
    return super.accessibilityParameterizedAttributeNames(id, sel);
  }
}
