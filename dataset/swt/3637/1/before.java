class PlaceHold {
  int processToggle(int path, int handle) {
    int length = OS.strlen(path);
    byte[] pathBytes = new byte[length + 1];
    OS.memmove(pathBytes, path, length);
    char[] pathChars = Converter.mbcsToWcs(null, pathBytes);
    int itemIndex = Integer.parseInt(new String(pathChars));
    boolean checked = items[itemIndex].getChecked();
    checked = !checked;
    items[itemIndex].setChecked(checked);
    return 0;
  }
}
