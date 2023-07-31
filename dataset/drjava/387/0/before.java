class PlaceHold {
  public static File makeRelativeTo(File f, File b) throws IOException, SecurityException {
    File base = b.getCanonicalFile();
    File abs = f.getCanonicalFile();
    if (!base.isDirectory()) {
      base = base.getParentFile();
    }
    String last = "";
    if (!abs.isDirectory()) {
      String tmp = abs.getPath();
      last = tmp.substring(tmp.lastIndexOf(File.separator) + 1);
      abs = abs.getParentFile();
    }
    String[] basParts = splitFile(base);
    String[] absParts = splitFile(abs);
    StringBuffer result = new StringBuffer();
    int diffIndex = -1;
    boolean different = false;
    for (int i = 0; i < basParts.length; i++) {
      if ((!different) && ((i >= absParts.length) || (!basParts[i].equals(absParts[i])))) {
        different = true;
        diffIndex = i;
      }
      if (different) {
        result.append("..").append(File.separator);
      }
    }
    if (diffIndex < 0) {
      diffIndex = basParts.length;
    }
    for (int i = diffIndex; i < absParts.length; i++) {
      result.append(absParts[i]).append(File.separator);
    }
    result.append(last);
    return new File(result.toString());
  }
}
