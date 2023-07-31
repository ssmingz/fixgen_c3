class PlaceHold {
  public String normalize(String aPath) {
    final String osName = System.getProperty("os.name").toLowerCase(Locale.US);
    final boolean onNetWare = osName.indexOf("netware") > (-1);
    final String orig = aPath;
    aPath = aPath.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    final int colon = aPath.indexOf(":");
    if (!onNetWare) {
      if ((!aPath.startsWith(File.separator))
          && (!(((aPath.length() >= 2) && Character.isLetter(aPath.charAt(0))) && (colon == 1)))) {
        final String msg = aPath + " is not an absolute path";
        throw new IllegalArgumentException(msg);
      }
    } else if ((!aPath.startsWith(File.separator)) && (colon == (-1))) {
      final String msg = aPath + " is not an absolute path";
      throw new IllegalArgumentException(msg);
    }
    boolean dosWithDrive = false;
    String root = null;
    if (((((!onNetWare) && (aPath.length() >= 2)) && Character.isLetter(aPath.charAt(0)))
            && (aPath.charAt(1) == ':'))
        || (onNetWare && (colon > (-1)))) {
      dosWithDrive = true;
      final char[] ca = aPath.replace('/', '\\').toCharArray();
      final StringBuffer sbRoot = new StringBuffer();
      for (int i = 0; i < colon; i++) {
        sbRoot.append(Character.toUpperCase(ca[i]));
      }
      sbRoot.append(':');
      if ((colon + 1) < aPath.length()) {
        sbRoot.append(File.separatorChar);
      }
      root = sbRoot.toString();
      final StringBuffer sbPath = new StringBuffer();
      for (int i = colon + 1; i < ca.length; i++) {
        if ((ca[i] != '\\') || ((ca[i] == '\\') && (ca[i - 1] != '\\'))) {
          sbPath.append(ca[i]);
        }
      }
      aPath = sbPath.toString().replace('\\', File.separatorChar);
    } else if (aPath.length() == 1) {
      root = File.separator;
      aPath = "";
    } else if (aPath.charAt(1) == File.separatorChar) {
      root = File.separator + File.separator;
      aPath = aPath.substring(2);
    } else {
      root = File.separator;
      aPath = aPath.substring(1);
    }
    final FastStack<String> s = FastStack.newInstance();
    s.push(root);
    final StringTokenizer tok = new StringTokenizer(aPath, File.separator);
    while (tok.hasMoreTokens()) {
      final String thisToken = tok.nextToken();
      if (".".equals(thisToken)) {
        continue;
      } else if ("..".equals(thisToken)) {
        if (s.size() < 2) {
          throw new IllegalArgumentException("Cannot resolve path " + orig);
        }
        s.pop();
      } else {
        s.push(thisToken);
      }
    }
    final StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.size(); i++) {
      if (i > 1) {
        sb.append(File.separatorChar);
      }
      sb.append(s.get(i));
    }
    aPath = sb.toString();
    if (dosWithDrive) {
      aPath = aPath.replace('/', '\\');
    }
    return aPath;
  }
}
