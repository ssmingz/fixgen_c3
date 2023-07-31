class PlaceHold {
  public void flush() throws IOException {
    OutputStream out = this.out;
    if (out != null) {
      out.flush();
    }
  }
}
