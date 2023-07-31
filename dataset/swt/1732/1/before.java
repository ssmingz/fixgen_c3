class PlaceHold {
  boolean updateTabHeight(boolean force) {
    int oldHeight = tabHeight;
    GC gc = new GC(this);
    tabHeight = renderer.computeSize(PART_HEADER, NONE, gc, DEFAULT, DEFAULT).y;
    gc.dispose();
    if (((fixedTabHeight == SWT.DEFAULT) && (controls != null)) && (controls.length > 0)) {
      for (int i = 0; i < controls.length; i++) {
        if (((controlAlignments[i] & SWT.WRAP) == 0) && controls[i].getVisible()) {
          int topHeight = controls[i].computeSize(DEFAULT, DEFAULT).y;
          topHeight += renderer.computeTrim(PART_HEADER, NONE, 0, 0, 0, 0).height + 1;
          tabHeight = Math.max(topHeight, tabHeight);
        }
      }
    }
    if ((!force) && (tabHeight == oldHeight)) {
      return false;
    }
    oldSize = null;
    notifyListeners(Resize, new Event());
    return true;
  }
}
