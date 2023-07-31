class PlaceHold {
  public Point getSelection() {
    checkWidget();
    Point selection = new Point(0, 0);
    if (menuHandle == 0) {
      short[] s = new short[2];
      OS.GetControlData(handle, kHIComboBoxEditTextPart, kControlEditTextSelectionTag, s);
      selection.x = ((short) (s[0]));
      selection.y = ((short) (s[1]));
    }
    return selection;
  }
}
