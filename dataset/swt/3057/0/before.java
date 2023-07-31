class PlaceHold {
  private void _selectAll() {
    String s = getText();
    short[] selection = new short[] {0, ((short) (s.length()))};
    OS.SetControlData(handle, kHIComboBoxEditTextPart, kControlEditTextSelectionTag, selection);
  }
}
