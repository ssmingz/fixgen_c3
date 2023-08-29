class PlaceHold {
  protected Object getTarget(Object element) {
    if (element instanceof IDisconnect) {
      return element;
    } else if (element instanceof IAdaptable) {
      return ((IAdaptable) element).getAdapter(IDisconnect.class);
    }
    return null;
  }
}
