class PlaceHold {
  public void setExcludes(String[] excludes) {
    if (excludes == null) {
      this.excludes = null;
    } else {
      this.excludes = new String[excludes.length];
      for (int i = 0; i < excludes.length; i++) {
        this.excludes[i] = normalizePattern(excludes[i]);
      }
    }
  }
}
