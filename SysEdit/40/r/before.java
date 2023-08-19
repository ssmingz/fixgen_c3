class PlaceHold {
  public org.eclipse.core.runtime.content.IContentTypeManager getContentTypeManager() {
    if (contentTracker == null) {
      contentTracker =
          new org.osgi.util.tracker.ServiceTracker(
              context, org.eclipse.core.runtime.content.IContentTypeManager.class.getName(), null);
      contentTracker.open();
    }
    return ((org.eclipse.core.runtime.content.IContentTypeManager) (contentTracker.getService()));
  }
}
