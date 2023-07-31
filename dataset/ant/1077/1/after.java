class PlaceHold {
  public void setIncludes(String[] includes) {
    if (includes == null) {
      this.includes = null;
    } else {
      this.includes = new String[includes.length];
      for (int i = 0; i < includes.length; i++) {
        this.includes[i] = normalizePattern(includes[i]);
      }
    }
  }
}
