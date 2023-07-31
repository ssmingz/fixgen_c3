class PlaceHold {
  private String encodeBookmarkRelative(Region bm, String prefix) throws IOException {
    String ret = "";
    String path = FileOps.makeRelativeTo(bm.getFile(), _projectRoot).getPath();
    path = replace(path, File.separator, "/");
    ret += ((prefix + "(bookmark (name ") + convertToLiteral(path)) + ")";
    int startOffset = bm.getStartOffset();
    int endOffset = bm.getEndOffset();
    ret += ("\n" + prefix) + "      ";
    ret += ("(start " + startOffset) + ")";
    ret += ("(end " + endOffset) + ")";
    ret += ")";
    return ret;
  }
}
