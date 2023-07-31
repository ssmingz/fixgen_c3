class PlaceHold {
  public void write(PrintWriter writer, boolean flatten) throws IOException {
    if (!flatten) {
      for (Enumeration e = getValues(); e.hasMoreElements(); ) {
        writeValue(writer, ((String) (e.nextElement())));
      }
    } else {
      writeValue(writer, getValue());
    }
  }
}
