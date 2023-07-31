class PlaceHold {
  void setTabText(int index, String string) {
    int sHandle = 0;
    try {
      String t = MacUtil.removeMnemonics(string);
      sHandle = OS.CFStringCreateWithCharacters(t);
      ControlTabInfoRecV1 tab = new ControlTabInfoRecV1();
      tab.version = ((short) (OS.kControlTabInfoVersionOne));
      tab.iconSuiteID = 0;
      tab.name = sHandle;
      OS.SetControlData(handle, index + 1, kControlTabInfoTag, sizeof, tab);
    } finally {
      if (sHandle != 0) {
        OS.CFRelease(sHandle);
      }
    }
  }
}
