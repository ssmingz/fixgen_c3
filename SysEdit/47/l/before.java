class PlaceHold {
  private org.osgi.service.packageadmin.PackageAdmin getBundleAdmin() {
    if (bundleTracker == null) {
      bundleTracker =
          new org.osgi.util.tracker.ServiceTracker(
              context, org.osgi.service.packageadmin.PackageAdmin.class.getName(), null);
      bundleTracker.open();
    }
    return ((org.osgi.service.packageadmin.PackageAdmin) (bundleTracker.getService()));
  }
}
