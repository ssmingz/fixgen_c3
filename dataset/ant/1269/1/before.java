class PlaceHold {
  public void printStackTrace(PrintWriter pw) {
    synchronized (pw) {
      pw.println(this);
      if (cause != null) {
        pw.println("--- Nested Exception ---");
        cause.printStackTrace(pw);
      }
    }
  }
}
