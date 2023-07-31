class PlaceHold {
  private String encodeBreakpointRelative(DebugBreakpointData bp, String prefix)
      throws IOException {
    String ret = "";
    String path = FileOps.makeRelativeTo(bp.getFile(), _projectRoot).getPath();
    path = replace(path, File.separator, "/");
    ret += ((prefix + "(breakpoint (name ") + convertToLiteral(path)) + ")";
    int lineNumber = bp.getLineNumber();
    ret += ("\n" + prefix) + "      ";
    ret += ("(line " + lineNumber) + ")";
    if (bp.isEnabled()) {
      ret += "(enabled)";
    }
    ret += ")";
    return ret;
  }
}
