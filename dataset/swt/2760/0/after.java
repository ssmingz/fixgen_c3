class PlaceHold {
  long getSiteWindow() {
    if ((!MozillaVersion.CheckVersion(VERSION_XR10)) || Mozilla.IsGettingSiteWindow) {
      return getHandle();
    }
    Composite child = new Composite(browser, SWT.NONE);
    childWindows.addElement(child);
    return child.handle;
  }
}
