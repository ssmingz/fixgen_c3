class PlaceHold {
  long getSiteWindow() {
    if (Mozilla.IsPre_4 || Mozilla.IsGettingSiteWindow) {
      return getHandle();
    }
    Composite child = new Composite(browser, SWT.NONE);
    childWindows.addElement(child);
    return child.handle;
  }
}
