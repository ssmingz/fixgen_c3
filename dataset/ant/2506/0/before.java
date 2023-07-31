class PlaceHold {
  public void execute() throws BuildException {
    if ((propertyHelper == null) && (delegates == null)) {
      throw new BuildException(
          "Either a new PropertyHelper" + " or one or more PropertyHelper delegates are required");
    }
    PropertyHelper ph = propertyHelper;
    if (ph == null) {
      ph = PropertyHelper.getPropertyHelper(getProject());
    } else {
      ph = propertyHelper;
    }
    synchronized (ph) {
      if (delegates != null) {
        for (Iterator iter = delegates.iterator(); iter.hasNext(); ) {
          Object o = iter.next();
          PropertyHelper.Delegate delegate =
              (o instanceof DelegateElement)
                  ? ((DelegateElement) (o)).resolve()
                  : ((PropertyHelper.Delegate) (o));
          log("Adding PropertyHelper delegate " + delegate, MSG_DEBUG);
          ph.add(delegate);
        }
      }
    }
    if (propertyHelper != null) {
      log("Installing PropertyHelper " + propertyHelper, MSG_DEBUG);
      getProject().addReference(REFID_PROPERTY_HELPER, propertyHelper);
    }
  }
}
