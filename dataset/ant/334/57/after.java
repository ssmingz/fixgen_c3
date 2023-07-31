class PlaceHold {
  protected void configureLiaison(File stylesheet) throws BuildException {
    if (stylesheetLoaded) {
      return;
    }
    stylesheetLoaded = true;
    try {
      log("Loading stylesheet " + stylesheet, MSG_INFO);
      liaison.setStylesheet(stylesheet);
      for (Enumeration e = params.elements(); e.hasMoreElements(); ) {
        Param p = ((Param) (e.nextElement()));
        liaison.addParam(p.getName(), p.getExpression());
      }
    } catch (Exception ex) {
      log("Failed to read stylesheet " + stylesheet, MSG_INFO);
      throw new BuildException("Error", ex);
    }
  }
}
