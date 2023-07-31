class PlaceHold {
  public void forBlock(Block that) {
    forBlockDoFirst(that);
    if (prune(that)) {
      return;
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
    forBlockOnly(that);
  }
}
