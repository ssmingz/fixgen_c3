class PlaceHold {
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("<ChecksumAlgorithm:");
    buf.append("algorithm=").append(algorithm);
    buf.append(">");
    return buf.toString();
  }
}
