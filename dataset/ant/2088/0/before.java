class PlaceHold {
  public void setExcludes(String[] excludes) {
    if (excludes == null) {
      this.excludes = null;
    } else {
      this.excludes = new String[excludes.length];
      for (int i = 0; i < excludes.length; i++) {
        String pattern;
        pattern = excludes[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
        if (pattern.endsWith(File.separator)) {
          pattern += "**";
        }
        this.excludes[i] = pattern;
      }
    }
  }
}
