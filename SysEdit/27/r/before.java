class PlaceHold {
  protected Object getTarget(Object element) {
    if (element instanceof org.eclipse.debug.core.model.IDropToFrame) {
      return element;
    } else if (element instanceof org.eclipse.core.runtime.IAdaptable) {
      return ((org.eclipse.core.runtime.IAdaptable) (element))
          .getAdapter(org.eclipse.debug.core.model.IDropToFrame.class);
    }
    return null;
  }
}
