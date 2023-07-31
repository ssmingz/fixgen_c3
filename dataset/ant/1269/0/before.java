class PlaceHold {
  public void printStackTrace(PrintStream ps) {
    synchronized (ps) {
      ps.println(this);
      if (cause != null) {
        ps.println("--- Nested Exception ---");
        cause.printStackTrace(ps);
      }
    }
  }
}
