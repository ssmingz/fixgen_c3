class PlaceHold {
  private PackageAdmin getBundleAdmin() {
    if (bundleTracker == null) {
      if (context == null) return null;
      bundleTracker = new ServiceTracker(context, PackageAdmin.class.getName(), null);
      bundleTracker.open();
    }
    return (PackageAdmin) bundleTracker.getService();
  }
}
