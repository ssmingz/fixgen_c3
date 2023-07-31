class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    int count = getItemCount();
    if (!(((0 <= start) && (start <= end)) && (start < count))) {
      error(ERROR_INVALID_RANGE);
    }
    int newEnd = Math.min(end, count - 1);
    if ((style & SWT.READ_ONLY) != 0) {
      OS.DeleteMenuItems(menuHandle, ((short) (start + 1)), (newEnd - start) + 1);
      int index = OS.GetControlValue(handle) - 1;
      if ((start <= index) && (index <= end)) {
        OS.SetControl32BitValue(handle, 0);
      }
    } else {
      for (int i = newEnd; i >= start; i--) {
        OS.HIComboBoxRemoveItemAtIndex(handle, i);
      }
    }
  }
}
