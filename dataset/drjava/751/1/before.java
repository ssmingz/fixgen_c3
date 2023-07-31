class PlaceHold {
  public String getHistoryAsString() {
    final StringBuilder sb = new StringBuilder();
    final String delimiter = System.getProperty("line.separator");
    for (String s : _vector) {
      sb.append(s).append(delimiter);
    }
    return sb.toString();
  }
}
