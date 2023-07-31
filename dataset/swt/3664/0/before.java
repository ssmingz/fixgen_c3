class PlaceHold {
  void updateCarbon(int startIndex) {
    int n = OS.GetControl32BitMaximum(handle);
    for (int i = startIndex; i < n; i++) {
      TabItem item = items[i];
      if (item != null) {
        int sHandle = 0;
        try {
          String t = removeMnemonics(item.getText());
          sHandle = OS.CFStringCreateWithCharacters(t);
          OS.setTabText(handle, i + 1, sHandle);
        } finally {
          if (sHandle != 0) {
            OS.CFRelease(sHandle);
          }
        }
      }
    }
    redraw();
  }
}
