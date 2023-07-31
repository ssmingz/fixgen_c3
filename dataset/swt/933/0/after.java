class PlaceHold {
  public void setText(String text) {
    checkLayout();
    if (text == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (text.equals(this.text)) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.text = text;
      styles = new StyleItem[2];
      styles[0] = new StyleItem();
      styles[1] = new StyleItem();
      styles[1].start = text.length();
      stylesCount = 2;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}
