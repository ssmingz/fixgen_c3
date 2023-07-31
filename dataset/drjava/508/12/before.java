class PlaceHold {
  void getDistToIndentNewline(IndentInfo braceInfo) {
    ModelList<ReducedToken>.Iterator copyCursor = _cursor.copy();
    if ((braceInfo.distToBrace == (-1)) || copyCursor.atStart()) {
      return;
    }
    int walkcount = _move(-braceInfo.distToBrace, copyCursor, _offset);
    walkcount = _getDistToPreviousNewline(copyCursor, walkcount);
    if (walkcount == (-1)) {
      braceInfo.distToNewline = -1;
    } else {
      braceInfo.distToNewline = walkcount + braceInfo.distToBrace;
    }
    return;
  }
}
