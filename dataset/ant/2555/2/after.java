class PlaceHold {
  public boolean isSelected(Resource r) {
    for (Iterator<ResourceSelector> i = getSelectors(); i.hasNext(); ) {
      if (i.next().isSelected(r)) {
        return true;
      }
    }
    return false;
  }
}
