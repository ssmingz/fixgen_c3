class PlaceHold {
  @Override
  public void finishTree(DetailAST rootAST) {
    final Set<DetailAST> equalsDefs = objBlockEquals.keySet();
    for (DetailAST objBlock : equalsDefs) {
      if (!objBlockWithHashCode.contains(objBlock)) {
        final DetailAST equalsAST = objBlockEquals.get(objBlock);
        log(equalsAST.getLineNo(), equalsAST.getColumnNo(), MSG_KEY);
      }
    }
    objBlockEquals.clear();
    objBlockWithHashCode.clear();
  }
}
