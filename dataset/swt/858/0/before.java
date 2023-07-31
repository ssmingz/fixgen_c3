class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int itemCount = OS.CountMenuItems(fMenuHandle);
    if (!((0 <= start) && (start < itemCount))) {
      return -1;
    }
    int[] sHandle = new int[1];
    for (int i = start; i < itemCount; i++) {
      OS.CopyMenuItemTextAsCFString(fMenuHandle, ((short) (i + 1)), sHandle);
      String s = MacUtil.getStringAndRelease(sHandle[0]);
      if (string.equals(s)) {
        return i;
      }
    }
    return -1;
  }
}
