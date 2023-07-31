class PlaceHold {
  public void setExcludesFile(URL excludeFile) throws ExecutionException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    this.excludeFile = excludeFile;
  }
}
