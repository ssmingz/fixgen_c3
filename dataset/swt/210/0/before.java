class PlaceHold {
  public int indexOf(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int itemCount = OS.CountMenuItems(fMenuHandle);
    int[] sHandle = new int[1];
    for (int i = 0; i < itemCount; i++) {
      OS.CopyMenuItemTextAsCFString(fMenuHandle, ((short) (i + 1)), sHandle);
      String s = MacUtil.getStringAndRelease(sHandle[0]);
      if ((s != null) && string.equals(s)) {
        return i;
      }
    }
    return -1;
  }
}
