class PlaceHold {
  Control getControl(int handle) {
    if (handle == 0) {
      return null;
    }
    int index = OS.GetWindowLong(handle, GWL_USERDATA) - 1;
    if ((0 <= index) && (index < controlTable.length)) {
      Control control = controlTable[index];
      if ((control != null) && control.checkHandle(handle)) {
        return control;
      }
    }
    return null;
  }
}
