class PlaceHold {
  public int LLBreakpointLineNum(Breakpoint breakpoint) {
    int line = breakpoint.getLineNumber();
    File f = breakpoint.getFile();
    if (DrJavaFileUtils.isLLFile(f)) {
      f = DrJavaFileUtils.getJavaForLLFile(f);
      TreeMap<Integer, Integer> tM = getLLSTM().ReadLanguageLevelLineBlockRev(f);
      line = tM.get(breakpoint.getLineNumber());
    }
    return line;
  }
}
