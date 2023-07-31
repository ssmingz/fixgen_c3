class PlaceHold {
  public static File normalize(String path) throws TaskException {
    String orig = path;
    path = path.replace('/', File.separatorChar).replace('\\', File.separatorChar);
    if ((!path.startsWith(File.separator))
        && (!(((path.length() >= 2) && Character.isLetter(path.charAt(0)))
            && (path.charAt(1) == ':')))) {
      String msg = path + " is not an absolute path";
      throw new TaskException(msg);
    }
    boolean dosWithDrive = false;
    String root = null;
    if (((path.length() >= 2) && Character.isLetter(path.charAt(0))) && (path.charAt(1) == ':')) {
      dosWithDrive = true;
      char[] ca = path.replace('/', '\\').toCharArray();
      StringBuffer sb = new StringBuffer();
      sb.append(Character.toUpperCase(ca[0])).append(':');
      for (int i = 2; i < ca.length; i++) {
        if ((ca[i] != '\\') || ((ca[i] == '\\') && (ca[i - 1] != '\\'))) {
          sb.append(ca[i]);
        }
      }
      path = sb.toString().replace('\\', File.separatorChar);
      if (path.length() == 2) {
        root = path;
        path = "";
      } else {
        root = path.substring(0, 3);
        path = path.substring(3);
      }
    } else if (path.length() == 1) {
      root = File.separator;
      path = "";
    } else if (path.charAt(1) == File.separatorChar) {
      root = File.separator + File.separator;
      path = path.substring(2);
    } else {
      root = File.separator;
      path = path.substring(1);
    }
    Stack s = new Stack();
    s.push(root);
    StringTokenizer tok = new StringTokenizer(path, File.separator);
    while (tok.hasMoreTokens()) {
      String thisToken = tok.nextToken();
      if (".".equals(thisToken)) {
        continue;
      } else if ("..".equals(thisToken)) {
        if (s.size() < 2) {
          throw new TaskException("Cannot resolve path " + orig);
        } else {
          s.pop();
        }
      } else {
        s.push(thisToken);
      }
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.size(); i++) {
      if (i > 1) {
        sb.append(File.separatorChar);
      }
      sb.append(s.elementAt(i));
    }
    path = sb.toString();
    if (dosWithDrive) {
      path = path.replace('/', '\\');
    }
    return new File(path);
  }
}
