class PlaceHold {
  boolean setRadioSelection(boolean value) {
    if ((style & SWT.RADIO) == 0) {
      return false;
    }
    if (getSelection() != value) {
      setSelection(value);
      postEvent(Selection);
    }
    return true;
  }
}
