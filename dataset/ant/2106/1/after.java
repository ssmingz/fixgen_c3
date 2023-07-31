class PlaceHold {
  @Override
  public String toString() {
    final StringBuilder buf = new StringBuilder("{presentselector targetdir: ");
    if (targetdir == null) {
      buf.append("NOT YET SET");
    } else {
      buf.append(targetdir.getName());
    }
    buf.append(" present: ");
    if (destmustexist) {
      buf.append("both");
    } else {
      buf.append("srconly");
    }
    if (map != null) {
      buf.append(map.toString());
    } else if (mapperElement != null) {
      buf.append(mapperElement.toString());
    }
    buf.append("}");
    return buf.toString();
  }
}
