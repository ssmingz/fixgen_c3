class PlaceHold {
  private PackageAdmin getBundleAdmin() {
    if (bundleTracker == null) {
      bundleTracker = new ServiceTracker(context, PackageAdmin.class.getName(), null);
      bundleTracker.open();
    }
    return (PackageAdmin) bundleTracker.getService();
  }
}
