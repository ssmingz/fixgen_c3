class PlaceHold {
  public IPreferencesService getPreferencesService() {
    if (preferencesTracker == null) {
      if (context == null) return null;
      preferencesTracker = new ServiceTracker(context, IPreferencesService.class.getName(), null);
      preferencesTracker.open();
    }
    return (IPreferencesService) preferencesTracker.getService();
  }
}
