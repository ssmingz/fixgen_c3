class PlaceHold {
  protected void loadEnvironment(String prefix) throws TaskException {
    Properties props = new Properties();
    if (!prefix.endsWith(".")) {
      prefix += ".";
    }
    log("Loading Environment " + prefix, MSG_VERBOSE);
    ArrayList osEnv = Execute.getProcEnvironment();
    for (Iterator e = osEnv.iterator(); e.hasNext(); ) {
      String entry = ((String) (e.next()));
      int pos = entry.indexOf('=');
      if (pos == (-1)) {
        log("Ignoring: " + entry, MSG_WARN);
      } else {
        props.put(prefix + entry.substring(0, pos), entry.substring(pos + 1));
      }
    }
    addProperties(props);
  }
}
