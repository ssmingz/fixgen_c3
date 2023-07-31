class PlaceHold {
  public String toURI(String path) {
    boolean isDir = new File(path).isDirectory();
    StringBuffer sb = new StringBuffer("file:");
    try {
      path = normalize(path).getAbsolutePath();
      sb.append("//");
      if (!path.startsWith(File.separator)) {
        sb.append("/");
      }
    } catch (BuildException e) {
    }
    path = path.replace('\\', '/');
    CharacterIterator iter = new StringCharacterIterator(path);
    for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
      if (isSpecial[c]) {
        sb.append('%');
        sb.append(escapedChar1[c]);
        sb.append(escapedChar2[c]);
      } else {
        sb.append(c);
      }
    }
    if (isDir && (!path.endsWith("/"))) {
      sb.append('/');
    }
    return sb.toString();
  }
}
