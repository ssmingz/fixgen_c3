class PlaceHold {
  public IContentTypeManager getContentTypeManager() {
    if (contentTracker == null) {
      contentTracker = new ServiceTracker(context, IContentTypeManager.class.getName(), null);
      contentTracker.open();
    }
    return (IContentTypeManager) contentTracker.getService();
  }
}
