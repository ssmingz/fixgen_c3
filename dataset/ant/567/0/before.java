class PlaceHold {
  public String toString() {
    StringBuffer sb = new StringBuffer("\n");
    int size = entries.size();
    for (int i = 0; i < size; ++i) {
      sb.append(((("[" + i) + "] = ") + getEntry(i)) + "\n");
    }
    return sb.toString();
  }
}
