class PlaceHold {
  public void setWarnings(boolean warnings) {
    checkDevice();
    if (warnings) {
      if ((--warningLevel) == 0) {
        if (debug) {
          return;
        }
        for (int i = 0; i < handler_ids.length; i++) {
          if (handler_ids[i] != 0) {
            byte[] log_domain = Converter.wcsToMbcs(null, log_domains[i], true);
            OS.g_log_remove_handler(log_domain, handler_ids[i]);
            handler_ids[i] = 0;
          }
        }
      }
    } else if ((warningLevel++) == 0) {
      int flags = (OS.G_LOG_LEVEL_MASK | OS.G_LOG_FLAG_FATAL) | OS.G_LOG_FLAG_RECURSION;
      for (int i = 0; i < log_domains.length; i++) {
        byte[] log_domain = Converter.wcsToMbcs(null, log_domains[i], true);
        handler_ids[i] = OS.g_log_set_handler(log_domain, flags, logProc, 0);
      }
    }
  }
}
