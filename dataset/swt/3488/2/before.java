class PlaceHold {
  public String getText() {
    checkWidget();
    if (fMenuHandle != 0) {
      int index = getSelectionIndex();
      if (index >= 0) {
        return _getItem(index);
      }
      return "";
    }
    int[] t = new int[1];
    OS.GetControlData(handle, kHIComboBoxEditTextPart, kControlEditTextCFStringTag, t);
    return MacUtil.getStringAndRelease(t[0]);
  }
}
