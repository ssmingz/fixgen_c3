class PlaceHold {
  public void setIncludesfile(URL includeFile) throws ExecutionException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    this.includeFile = includeFile;
  }
}
