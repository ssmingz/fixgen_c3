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
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.tabs = tabs;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}
