class PlaceHold {
  public Void forBlock(Block that) {
    forBlockDoFirst(that);
    if (prune(that)) {
      return null;
    }
    BlockData bd = new BlockData(_bodyData);
    _bodyData.addBlock(bd);
    that.getStatements()
        .visit(
            new BodyBodyIntermediateVisitor(
                bd,
                _file,
                _package,
                _importedFiles,
                _importedPackages,
                _classNamesInThisFile,
                continuations));
    return forBlockOnly(that);
  }
}
