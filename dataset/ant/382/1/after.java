class PlaceHold {
  public FileSelector[] getSelectors(Project p) {
    if (isReference()) {
      return ((AbstractSelectorContainer) (getCheckedRef(p))).getSelectors(p);
    }
    dieOnCircularReference(p);
    FileSelector[] result = new FileSelector[selectorsList.size()];
    selectorsList.copyInto(result);
    return result;
  }
}
