class PlaceHold {
  public void appendExcludes(String[] excludes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if (excludes != null) {
      for (int i = 0; i < excludes.length; i++) {
        defaultPatterns.createExclude().setName(excludes[i]);
      }
    }
  }
}
