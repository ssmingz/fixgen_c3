class PlaceHold {
  public FileNameMapper getImplementation() throws BuildException {
    if (isReference()) {
      return getRef().getImplementation();
    }
    if (((type == null) && (classname == null)) && (container == null)) {
      throw new BuildException(
          "nested mapper or " + "one of the attributes type or classname is required");
    }
    if (container != null) {
      return container;
    }
    if ((type != null) && (classname != null)) {
      throw new BuildException("must not specify both type and classname attribute");
    }
    try {
      FileNameMapper m = ((FileNameMapper) (getImplementationClass().newInstance()));
      final Project p = getProject();
      if (p != null) {
        p.setProjectReference(m);
      }
      m.setFrom(from);
      m.setTo(to);
      return m;
    } catch (BuildException be) {
      throw be;
    } catch (Throwable t) {
      throw new BuildException(t);
    }
  }
}
