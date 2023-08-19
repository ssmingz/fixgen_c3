class PlaceHold {
  public org.eclipse.core.runtime.preferences.IPreferencesService getPreferencesService() {
    if (preferencesTracker == null) {
      if (context == null) return null;

      preferencesTracker =
          new org.osgi.util.tracker.ServiceTracker(
              context,
              org.eclipse.core.runtime.preferences.IPreferencesService.class.getName(),
              null);
      preferencesTracker.open();
    }
    return ((org.eclipse.core.runtime.preferences.IPreferencesService)
        (preferencesTracker.getService()));
  }
}
