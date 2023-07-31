class PlaceHold {
  public FileNameMapper getImplementation() throws BuildException {
    if (isReference()) {
      return getRef().getImplementation();
    }
    if ((type == null) && (classname == null)) {
      throw new BuildException("one of the attributes type or classname is required");
    }
    if ((type != null) && (classname != null)) {
      throw new BuildException("must not specify both type and classname attribute");
    }
    try {
      if (type != null) {
        classname = type.getImplementation();
      }
      Class c = null;
      if (classpath == null) {
        c = Class.forName(classname);
      } else {
        AntClassLoader al = new AntClassLoader(getProject(), classpath);
        c = al.loadClass(classname);
        AntClassLoader.initializeClass(c);
      }
      FileNameMapper m = ((FileNameMapper) (c.newInstance()));
      m.setFrom(from);
      m.setTo(to);
      return m;
    } catch (BuildException be) {
      throw be;
    } catch (Throwable t) {
      throw new BuildException(t);
    } finally {
      if (type != null) {
        classname = null;
      }
    }
  }
}
