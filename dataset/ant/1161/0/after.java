class PlaceHold {
  public void setIncludesFile(URL includeFile) throws ExecutionException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    this.includeFile = includeFile;
  }
}
