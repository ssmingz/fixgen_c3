class PlaceHold {
  public void setTabs(int[] tabs) {
    checkLayout();
    if ((this.tabs == null) && (tabs == null)) {
      return;
    }
    if ((this.tabs != null) && (tabs != null)) {
      if (this.tabs.length == tabs.length) {
        int i;
        for (i = 0; i < tabs.length; i++) {
          if (this.tabs[i] != tabs[i]) {
            break;
          }
        }
        if (i == tabs.length) {
          return;
        }
      }
    }
    freeRuns();
    this.tabs = tabs;
    if (tabsPtr != 0) {
      OS.DisposePtr(tabsPtr);
    }
    tabsPtr = 0;
    if (tabs == null) {
      OS.ATSUSetTabArray(layout, 0, 0);
    } else {
      ATSUTab tab = new ATSUTab();
      tab.tabPosition = OS.Long2Fix(0);
      int length = Math.max(TAB_COUNT, tabs.length);
      int ptr = tabsPtr = OS.NewPtr(ATSUTab.sizeof * length);
      int i;
      int offset;
      for (i = 0, offset = ptr; i < tabs.length; i++, offset += ATSUTab.sizeof) {
        tab.tabType = OS.kATSULeftTab;
        tab.tabPosition += OS.Long2Fix(tabs[i]);
        OS.memcpy(offset, tab, sizeof);
      }
      int width = ((i - 2) >= 0) ? tabs[i - 1] - tabs[i - 2] : tabs[i - 1];
      if (width != 0) {
        for (; i < length; i++, offset += ATSUTab.sizeof) {
          tab.tabType = OS.kATSULeftTab;
          tab.tabPosition += OS.Long2Fix(width);
          OS.memcpy(offset, tab, sizeof);
        }
      }
      OS.ATSUSetTabArray(layout, ptr, i);
    }
  }
}
