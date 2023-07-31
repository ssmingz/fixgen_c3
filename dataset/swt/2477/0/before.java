class PlaceHold {
  int accessibilityFocusedUIElement(int id, int sel) {
    id returnValue = null;
    if ((id == view.id)
        || (((view instanceof NSControl) && (((NSControl) (view)).cell() != null))
            && (((NSControl) (view)).cell().id == id))) {
      if (accessible != null) {
        returnValue = accessible.internal_accessibilityFocusedUIElement(CHILDID_SELF);
      }
    }
    if (returnValue == null) {
      return super.accessibilityFocusedUIElement(id, sel);
    } else {
      return returnValue.id;
    }
  }
}
