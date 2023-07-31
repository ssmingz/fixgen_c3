class PlaceHold {
  long foregroundIdleProc(long code, long wParam, long lParam) {
    if (code >= 0) {
      if (runMessages && (getMessageCount() != 0)) {
        sendWakeupEvent();
        if (runMessagesInIdle) {
          if (runMessagesInMessageProc) {
            OS.PostMessage(hwndMessage, SWT_RUNASYNC, 0, 0);
          } else {
            runAsyncMessages(false);
          }
        }
        MSG msg = new MSG();
        int flags = (OS.PM_NOREMOVE | OS.PM_NOYIELD) | OS.PM_QS_INPUT;
        if (!OS.PeekMessage(msg, 0, 0, 0, flags)) {
          wakeThread();
        }
        sendSleepEvent();
      }
    }
    return OS.CallNextHookEx(idleHook, ((int) (code)), wParam, lParam);
  }
}
