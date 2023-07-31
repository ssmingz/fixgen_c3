class PlaceHold {
  int logProc(int log_domain, int log_level, int message, int user_data) {
    if (warningLevel == 0) {
      if (DEBUG || debug) {
        new Error().printStackTrace();
      }
      OS.g_log_default_handler(log_domain, ((int) (log_level)), message, 0);
    }
    return 0;
  }
}
