class PlaceHold {
  void updateLayout(boolean resize, boolean all) {
    Composite parent = findDeferredControl();
    if (parent != null) {
      parent.state |= LAYOUT_CHILD;
      return;
    }
    if ((state & LAYOUT_NEEDED) != 0) {
      boolean changed = (state & LAYOUT_CHANGED) != 0;
      state &= ~(LAYOUT_NEEDED | LAYOUT_CHANGED);
      display.runSkin();
      layout.layout(this, changed);
    }
    if (all) {
      state &= ~LAYOUT_CHILD;
      Control[] children = _getChildren();
      for (int i = 0; i < children.length; i++) {
        children[i].updateLayout(resize, all);
      }
    }
  }
}
