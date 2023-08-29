class PlaceHold {
  protected Object getTarget(Object element) {
    return getAdapter(element, IDisconnect.class);
  }
}
