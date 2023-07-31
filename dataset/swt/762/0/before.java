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
    this.tabs = tabs;
    if (tabs == null) {
      OS.pango_layout_set_tabs(layout, emptyTab);
    } else {
      int tabArray = OS.pango_tab_array_new(tabs.length, true);
      if (tabArray != 0) {
        for (int i = 0; i < tabs.length; i++) {
          OS.pango_tab_array_set_tab(tabArray, i, PANGO_TAB_LEFT, tabs[i]);
        }
        OS.pango_layout_set_tabs(layout, tabArray);
        OS.pango_tab_array_free(tabArray);
      }
    }
    OS.pango_layout_context_changed(layout);
  }
}
