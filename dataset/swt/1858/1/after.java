class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return true;
    }
    int result;
    int status;
    boolean workProc = true;
    int display_fd = OS.ConnectionNumber(xDisplay);
    int xtContext = OS.XtDisplayToApplicationContext(xDisplay);
    int max_fd = (display_fd > read_fd) ? display_fd : read_fd;
    do {
      OS.FD_ZERO(fd_set);
      OS.FD_SET(display_fd, fd_set);
      OS.FD_SET(read_fd, fd_set);
      timeout[0] = 0;
      timeout[1] = 50000;
      int count = Callback.getEntryCount();
      for (int i = 0; i < count; i++) {
        synchronized (OS_LOCK) {
          OS.MonitorExit(OS_LOCK);
        }
      }
      synchronized (OS_LOCK) {
        OS_LOCK.notifyAll();
      }
      try {
        result = OS.select(max_fd + 1, fd_set, null, null, timeout);
      } finally {
        for (int i = 0; i < count; i++) {
          synchronized (OS_LOCK) {
            OS.MonitorEnter(OS_LOCK);
          }
        }
      }
      status = OS.XtAppPending(xtContext);
      if (workProc && (status == 0)) {
        workProc = false;
        OS.XtAppAddTimeOut(xtContext, 1, 0, 0);
        OS.XtAppProcessEvent(xtContext, XtIMTimer);
      }
    } while (((result == 0) && (getMessageCount() == 0)) && (status == 0));
    return OS.FD_ISSET(display_fd, fd_set);
  }
}
