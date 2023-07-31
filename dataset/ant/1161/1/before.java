class PlaceHold {
  public void setExcludesfile(URL excludeFile) throws ExecutionException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    this.excludeFile = excludeFile;
  }
}
