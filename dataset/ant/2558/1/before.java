class PlaceHold {
  public String[] mapFileName(String name) {
    if (((((name == null) || (!name.endsWith(".class")))
                || name.endsWith(getStubClassSuffix() + ".class"))
            || name.endsWith(getSkelClassSuffix() + ".class"))
        || name.endsWith(getTieClassSuffix() + ".class")) {
      return null;
    }
    String base = name.substring(0, name.length() - 6);
    String classname = base.replace(File.separatorChar, '.');
    if (attributes.getVerify() && (!attributes.isValidRmiRemote(classname))) {
      return null;
    }
    String[] target = new String[] {(name + ".tmp.") + RAND.nextLong()};
    if ((!attributes.getIiop()) && (!attributes.getIdl())) {
      if ("1.2".equals(attributes.getStubVersion())) {
        target = new String[] {(base + getStubClassSuffix()) + ".class"};
      } else {
        target =
            new String[] {
              (base + getStubClassSuffix()) + ".class", (base + getSkelClassSuffix()) + ".class"
            };
      }
    } else if (!attributes.getIdl()) {
      int lastSlash = base.lastIndexOf(File.separatorChar);
      String dirname = "";
      int index = -1;
      if (lastSlash == (-1)) {
        index = 0;
      } else {
        index = lastSlash + 1;
        dirname = base.substring(0, index);
      }
      String filename = base.substring(index);
      try {
        Class c = attributes.getLoader().loadClass(classname);
        if (c.isInterface()) {
          target = new String[] {(((dirname + "_") + filename) + getStubClassSuffix()) + ".class"};
        } else {
          Class interf = attributes.getRemoteInterface(c);
          String iName = interf.getName();
          String iDir = "";
          int iIndex = -1;
          int lastDot = iName.lastIndexOf(".");
          if (lastDot == (-1)) {
            iIndex = 0;
          } else {
            iIndex = lastDot + 1;
            iDir = iName.substring(0, iIndex);
            iDir = iDir.replace('.', File.separatorChar);
          }
          target =
              new String[] {
                (((dirname + "_") + filename) + getTieClassSuffix()) + ".class",
                (((iDir + "_") + iName.substring(iIndex)) + getStubClassSuffix()) + ".class"
              };
        }
      } catch (ClassNotFoundException e) {
        attributes.log(
            ("Unable to verify class " + classname) + ". It could not be found.", MSG_WARN);
      } catch (NoClassDefFoundError e) {
        attributes.log(("Unable to verify class " + classname) + ". It is not defined.", MSG_WARN);
      } catch (Throwable t) {
        attributes.log(
            (("Unable to verify class " + classname) + ". Loading caused Exception: ")
                + t.getMessage(),
            MSG_WARN);
      }
    }
    return target;
  }
}
