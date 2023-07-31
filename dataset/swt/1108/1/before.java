class PlaceHold {
  public static void showWhile(Display display, Runnable runnable) {
    if (display == null) {
      display = Display.getCurrent();
      if (display == null) {
        runnable.run();
        return;
      }
    }
    Integer busyId = new Integer(nextBusyId);
    nextBusyId++;
    Cursor cursor = new Cursor(display, SWT.CURSOR_WAIT);
    Shell[] shells = display.getShells();
    for (int i = 0; i < shells.length; i++) {
      Integer id = ((Integer) (shells[i].getData(BUSYID_NAME)));
      if (id == null) {
        shells[i].setCursor(cursor);
        shells[i].setData(BUSYID_NAME, busyId);
      }
    }
    try {
      runnable.run();
    } finally {
      shells = display.getShells();
      for (int i = 0; i < shells.length; i++) {
        Integer id = ((Integer) (shells[i].getData(BUSYID_NAME)));
        if (id == busyId) {
          shells[i].setCursor(null);
          shells[i].setData(BUSYID_NAME, null);
        }
      }
      if ((cursor != null) && (!cursor.isDisposed())) {
        cursor.dispose();
      }
    }
  }
}
