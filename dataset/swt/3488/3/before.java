class PlaceHold {
  public void setSelection(Point selection) {
    checkWidget();
    if (fMenuHandle == 0) {
      short[] s = new short[] {((short) (selection.x)), ((short) (selection.y))};
      OS.SetControlData(handle, kHIComboBoxEditTextPart, kControlEditTextSelectionTag, s);
    }
  }
}
