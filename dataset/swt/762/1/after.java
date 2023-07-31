class PlaceHold {
  void setTabStops(int tabs) {
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    int tabWidth = getTabWidth(tabs);
    long tabArray = OS.pango_tab_array_new(1, false);
    OS.pango_tab_array_set_tab(tabArray, 0, PANGO_TAB_LEFT, tabWidth);
    OS.gtk_text_view_set_tabs(handle, tabArray);
    OS.pango_tab_array_free(tabArray);
  }
}
