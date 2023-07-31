class PlaceHold {
  void selectRadio() {
    Control[] children = parent._getChildren();
    for (int i = 0; i < children.length; i++) {
      Control child = children[i];
      if ((this != child) && (child instanceof Button)) {
        Button button = ((Button) (child));
        if ((button.getStyle() & SWT.RADIO) != 0) {
          if (button.getSelection()) {
            button.setSelection(false);
            button.postEvent(Selection);
          }
        }
      }
    }
    setSelection(true);
  }
}
