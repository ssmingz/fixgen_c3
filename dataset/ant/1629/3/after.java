class PlaceHold {
  JUnitResultFormatter createFormatter(ClassLoader loader) throws BuildException {
    if (classname == null) {
      throw new BuildException("you must specify type or classname");
    }
    Class f = null;
    try {
      if (loader == null) {
        f = Class.forName(classname);
      } else {
        f = Class.forName(classname, true, loader);
      }
    } catch (ClassNotFoundException e) {
      throw new BuildException(e);
    }
    Object o = null;
    try {
      o = f.newInstance();
    } catch (InstantiationException e) {
      throw new BuildException(e);
    } catch (IllegalAccessException e) {
      throw new BuildException(e);
    }
    if (!(o instanceof JUnitResultFormatter)) {
      throw new BuildException(classname + " is not a JUnitResultFormatter");
    }
    JUnitResultFormatter r = ((JUnitResultFormatter) (o));
    if (useFile && (outFile != null)) {
      try {
        out = new FileOutputStream(outFile);
      } catch (IOException e) {
        throw new BuildException(e);
      }
    }
    r.setOutput(out);
    return r;
  }
}
