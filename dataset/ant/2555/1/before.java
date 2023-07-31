class PlaceHold {
  public boolean isSelected(Resource r) {
    for (Iterator i = getSelectors(); i.hasNext(); ) {
      if (!((ResourceSelector) (i.next())).isSelected(r)) {
        return false;
      }
    }
    return true;
  }
}
