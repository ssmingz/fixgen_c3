class PlaceHold {
  public String removeLeadingPath(File leading, File path) {
    String l = normalize(leading.getAbsolutePath()).getAbsolutePath();
    String p = normalize(path.getAbsolutePath()).getAbsolutePath();
    if (l.equals(p)) {
      return "";
    }
    if (!l.endsWith(File.separator)) {
      l += File.separator;
    }
    if (p.startsWith(l)) {
      return p.substring(l.length());
    } else {
      return p;
    }
  }
}
