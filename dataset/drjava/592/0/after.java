class PlaceHold {
  private String encodeDocFile(DocFile df, String prefix, boolean relative) throws IOException {
    String ret = "";
    String path;
    if (relative) {
      path = FileOps.stringMakeRelativeTo(df, _projectRoot);
    } else {
      path = IOUtil.attemptCanonicalFile(df).getPath();
    }
    path = replace(path, File.separator, "/");
    ret += ((prefix + "(file (name ") + convertToLiteral(path)) + ")";
    Pair<Integer, Integer> p1 = df.getSelection();
    Pair<Integer, Integer> p2 = df.getScroll();
    long modDate = df.lastModified();
    if ((p1 != null) || (p2 != null)) {
      ret += ("\n" + prefix) + "      ";
    }
    if (p1 != null) {
      ret += ((("(select " + p1.first()) + " ") + p1.second()) + ")";
    }
    if (p2 != null) {
      ret += ((("(scroll " + p2.first()) + " ") + p2.second()) + ")";
    }
    if (modDate > 0) {
      String s = MOD_DATE_FORMAT.format(new Date(modDate));
      ret += ("(mod-date " + convertToLiteral(s)) + ")";
    }
    String pack = df.getPackage();
    if (pack != null) {
      ret += ("\n" + prefix) + "      ";
      ret += ("(package " + convertToLiteral(pack)) + ")";
    }
    ret += ")";
    return ret;
  }
}
