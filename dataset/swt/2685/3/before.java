class PlaceHold {
  protected void release() {
    sendEvent(Dispose, new Event());
    Shell[] shells = WidgetTable.shells();
    for (int i = 0; i < shells.length; i++) {
      Shell shell = shells[i];
      if (!shell.isDisposed()) {
        if (this == shell.getDisplay()) {
          shell.dispose();
        }
      }
    }
    while (readAndDispatch()) {}
    if (disposeList != null) {
      for (int i = 0; i < disposeList.length; i++) {
        if (disposeList[i] != null) {
          disposeList[i].run();
        }
      }
    }
    disposeList = null;
    synchronizer.releaseSynchronizer();
    synchronizer = null;
    releaseDisplay();
    super.release();
  }
}
