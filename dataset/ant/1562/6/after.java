class PlaceHold {
  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append("<ChecksumAlgorithm:");
    buf.append("algorithm=").append(algorithm);
    buf.append(">");
    return buf.toString();
  }
}
