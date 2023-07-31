class PlaceHold {
  public void setSelection(Point selection) {
    checkWidget();
    if (menuHandle == 0) {
      short[] s = new short[] {((short) (selection.x)), ((short) (selection.y))};
      OS.SetControlData(
          handle, kHIComboBoxEditTextPart, kControlEditTextSelectionTag, s.length * 2, s);
    }
  }
}
