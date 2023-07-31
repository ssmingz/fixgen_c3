class PlaceHold {
  public void write(PrintWriter writer, boolean flatten) throws IOException {
    if (!flatten) {
      for (Enumeration<String> e = getValues(); e.hasMoreElements(); ) {
        writeValue(writer, e.nextElement());
      }
    } else {
      writeValue(writer, getValue());
    }
  }
}
