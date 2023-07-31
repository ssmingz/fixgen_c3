class PlaceHold {
  protected void _init() {
    if (_writer == null) {
      if (_enabled || ENABLE_ALL) {
        try {
          File f = new File(_name);
          FileWriter w = new FileWriter(f.getAbsolutePath(), true);
          _writer = new PrintWriter(w);
          logTime((("Log '" + _name) + "' opened: ") + new Date());
        } catch (IOException ioe) {
          throw new RuntimeException("Could not create log: " + ioe);
        }
      }
    }
  }
}
