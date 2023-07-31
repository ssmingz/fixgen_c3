class PlaceHold {
  boolean setRadioSelection(boolean value) {
    if ((style & SWT.RADIO) == 0) {
      return false;
    }
    if (getSelection() != value) {
      setSelection(value);
      sendSelectionEvent(Selection);
    }
    return true;
  }
}
