class PlaceHold {
  int accessibilityParameterizedAttributeNames(int id, int sel) {
    if ((id == view.id)
        || (((view instanceof NSControl) && (((NSControl) (view)).cell() != null))
            && (((NSControl) (view)).cell().id == id))) {
      if (accessible != null) {
        NSArray returnValue =
            accessible.internal_accessibilityParameterizedAttributeNames(CHILDID_SELF);
        if (returnValue != null) {
          return returnValue.id;
        }
      }
    }
    return super.accessibilityParameterizedAttributeNames(id, sel);
  }
}
