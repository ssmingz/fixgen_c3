class PlaceHold {
  public String toURI(String path) {
    Class uriClazz = null;
    try {
      uriClazz = Class.forName("java.net.URI");
    } catch (ClassNotFoundException e) {
    }
    if (uriClazz != null) {
      try {
        File f = new File(path).getAbsoluteFile();
        Method toURIMethod = File.class.getMethod("toURI", new Class[0]);
        Object uriObj = toURIMethod.invoke(f, new Object[] {});
        Method toASCIIStringMethod = uriClazz.getMethod("toASCIIString", new Class[0]);
        return ((String) (toASCIIStringMethod.invoke(uriObj, new Object[] {})));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    boolean isDir = new File(path).isDirectory();
    StringBuffer sb = new StringBuffer("file:");
    path = resolveFile(null, path).getPath();
    sb.append("//");
    if (!path.startsWith(File.separator)) {
      sb.append("/");
    }
    path = path.replace('\\', '/');
    try {
      sb.append(Locator.encodeURI(path));
    } catch (UnsupportedEncodingException exc) {
      throw new BuildException(exc);
    }
    if (isDir && (!path.endsWith("/"))) {
      sb.append('/');
    }
    return sb.toString();
  }
}
