class PlaceHold {
  public String getHistoryAsStringWithSemicolons() {
    final StringBuilder s = new StringBuilder();
    final String delimiter = INTERACTION_SEPARATOR + StringOps.EOL;
    for (int i = 0; i < _vector.size(); i++) {
      String nextLine = _vector.get(i);
      s.append(nextLine);
      s.append(delimiter);
    }
    return s.toString();
  }
}
