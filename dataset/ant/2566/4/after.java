class PlaceHold {
  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append("<DigestAlgorithm:");
    buf.append("algorithm=").append(algorithm);
    buf.append(";provider=").append(provider);
    buf.append(">");
    return buf.toString();
  }
}
