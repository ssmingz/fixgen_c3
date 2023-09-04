class PlaceHold {
  protected Object getTarget(Object element) {
    if (element instanceof IDropToFrame) {
      return element;
    } else if (element instanceof IAdaptable) {
      return ((IAdaptable) element).getAdapter(IDropToFrame.class);
    }
    return null;
  }
}
