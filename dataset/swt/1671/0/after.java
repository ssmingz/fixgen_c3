class PlaceHold {
  public void setSelection(int index) {
    checkWidget();
    int count = OS.SendMessage(handle, TCM_GETITEMCOUNT, 0, 0);
    if (!((0 <= index) && (index < count))) {
      return;
    }
    setSelection(index, false);
  }
}
