class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return true;
    }
    if (fds == 0) {
      allocated_nfds = 2;
      fds = OS.g_malloc(OS.GPollFD_sizeof() * allocated_nfds);
    }
    max_priority[0] = timeout[0] = 0;
    int context = OS.g_main_context_default();
    boolean result = false;
    do {
      if (OS.g_main_context_acquire(context)) {
        result = OS.g_main_context_prepare(context, max_priority);
        int nfds;
        while ((nfds =
                OS.g_main_context_query(context, max_priority[0], timeout, fds, allocated_nfds))
            > allocated_nfds) {
          OS.g_free(fds);
          allocated_nfds = nfds;
          fds = OS.g_malloc(OS.GPollFD_sizeof() * allocated_nfds);
        }
        int poll = OS.g_main_context_get_poll_func(context);
        if (poll != 0) {
          if ((nfds > 0) || (timeout[0] != 0)) {
            if (timeout[0] < 0) {
              timeout[0] = 50;
            }
            int count = Callback.getEntryCount();
            for (int i = 0; i < count; i++) {
              synchronized (OS_LOCK) {
                OS.MonitorExit(OS_LOCK);
              }
            }
            try {
              wake = false;
              OS.Call(poll, fds, nfds, timeout[0]);
            } finally {
              for (int i = 0; i < count; i++) {
                synchronized (OS_LOCK) {
                  OS.MonitorEnter(OS_LOCK);
                }
              }
            }
          }
        }
        OS.g_main_context_check(context, max_priority[0], fds, nfds);
        OS.g_main_context_release(context);
      }
    } while (((!result) && (getMessageCount() == 0)) && (!wake));
    wake = false;
    return true;
  }
}
