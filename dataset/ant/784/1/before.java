class PlaceHold {
  public void appendIncludes(String[] includes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if (includes != null) {
      for (int i = 0; i < includes.length; i++) {
        defaultPatterns.createInclude().setName(includes[i]);
      }
    }
  }
}
