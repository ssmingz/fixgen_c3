class PlaceHold {
  protected Object getTarget(Object element) {
    return getAdapter(element, org.eclipse.debug.core.model.IDropToFrame.class);
  }
}
