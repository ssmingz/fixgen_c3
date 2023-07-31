class PlaceHold {
  public FileSelector[] getSelectors(Project p) {
    FileSelector[] result = new FileSelector[selectorsList.size()];
    selectorsList.copyInto(result);
    return result;
  }
}
