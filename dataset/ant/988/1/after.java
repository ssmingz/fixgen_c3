class PlaceHold {
  public static boolean isOs(String family, String name, String arch, String version) {
    boolean retValue = false;
    if ((((family != null) || (name != null)) || (arch != null)) || (version != null)) {
      boolean isFamily = true;
      boolean isName = true;
      boolean isArch = true;
      boolean isVersion = true;
      if (family != null) {
        if (family.equals("windows")) {
          isFamily = osName.indexOf("windows") > (-1);
        } else if (family.equals("os/2")) {
          isFamily = osName.indexOf("os/2") > (-1);
        } else if (family.equals("netware")) {
          isFamily = osName.indexOf("netware") > (-1);
        } else if (family.equals("dos")) {
          isFamily = pathSep.equals(";") && (!isFamily("netware"));
        } else if (family.equals("mac")) {
          isFamily = osName.indexOf("mac") > (-1);
        } else if (family.equals("tandem")) {
          isFamily = osName.indexOf("nonstop_kernel") > (-1);
        } else if (family.equals("unix")) {
          isFamily = pathSep.equals(":") && ((!isFamily("mac")) || osName.endsWith("x"));
        } else if (family.equals("win9x")) {
          isFamily =
              isFamily("windows")
                  && ((((osName.indexOf("95") >= 0) || (osName.indexOf("98") >= 0))
                          || (osName.indexOf("me") >= 0))
                      || (osName.indexOf("ce") >= 0));
        } else if (family.equals("z/os")) {
          isFamily = (osName.indexOf("z/os") > (-1)) || (osName.indexOf("os/390") > (-1));
        } else if (family.equals("os/400")) {
          isFamily = osName.indexOf("os/400") > (-1);
        } else if (family.equals("openvms")) {
          isFamily = osName.indexOf("openvms") > (-1);
        } else {
          throw new BuildException(("Don\'t know how to detect os family \"" + family) + "\"");
        }
      }
      if (name != null) {
        isName = name.equals(osName);
      }
      if (arch != null) {
        isArch = arch.equals(osArch);
      }
      if (version != null) {
        isVersion = version.equals(osVersion);
      }
      retValue = ((isFamily && isName) && isArch) && isVersion;
    }
    return retValue;
  }
}
