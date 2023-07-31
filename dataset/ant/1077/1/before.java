class PlaceHold {
  public void setIncludes(String[] includes) {
    if (includes == null) {
      this.includes = null;
    } else {
      this.includes = new String[includes.length];
      for (int i = 0; i < includes.length; i++) {
        String pattern;
        pattern = includes[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
        if (pattern.endsWith(File.separator)) {
          pattern += "**";
        }
        this.includes[i] = pattern;
      }
    }
  }
}
