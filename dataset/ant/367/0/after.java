class PlaceHold {
  public void flush() throws IOException {
    OutputStream outShadow = this.out;
    if (outShadow != null) {
      outShadow.flush();
    }
  }
}
