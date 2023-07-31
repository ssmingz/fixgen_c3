class PlaceHold {
  public void setErrorPrintStream(PrintStream err) {
    this.err = new PrintStream(err, true);
  }
}
