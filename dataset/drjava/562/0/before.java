class PlaceHold {
  public int LLDDALineNum(DocumentDebugAction<?> dda) {
    int line = dda.getLineNumber();
    File f = dda.getFile();
    if (DrJavaFileUtils.isLLFile(f)) {
      f = DrJavaFileUtils.getJavaForLLFile(f);
      TreeMap<Integer, Integer> tM = _manager.getLLSTM().ReadLanguageLevelLineBlockRev(f);
      line = tM.get(dda.getLineNumber());
    }
    return line;
  }
}
