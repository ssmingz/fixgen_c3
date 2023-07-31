class PlaceHold {
  void selectRadio() {
    Control[] children = parent._getChildren();
    for (int i = 0; i < children.length; i++) {
      Control child = children[i];
      if (this != child) {
        child.setRadioSelection(false);
      }
    }
    setSelection(true);
  }
}
