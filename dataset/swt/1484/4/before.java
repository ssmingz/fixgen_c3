class PlaceHold {
  boolean runDeferredLayouts() {
    if (layoutDeferredCount != 0) {
      Composite[] temp = layoutDeferred;
      int count = layoutDeferredCount;
      layoutDeferred = null;
      layoutDeferredCount = 0;
      for (int i = 0; i < count; i++) {
        Composite comp = temp[i];
        if (!comp.isDisposed()) {
          comp.setLayoutDeferred(false);
        }
      }
      return true;
    }
    return false;
  }
}
