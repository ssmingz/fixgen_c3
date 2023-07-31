class PlaceHold {
  protected RegexpMatcher createInstance(String className) throws BuildException {
    try {
      Class implClass = Class.forName(className);
      return ((RegexpMatcher) (implClass.newInstance()));
    } catch (Throwable t) {
      throw new BuildException("Error", t);
    }
  }
}
