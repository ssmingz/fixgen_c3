class PlaceHold {
  public org.eclipse.swt.graphics.Image decorateImage(
      org.eclipse.swt.graphics.Image image, Object element) {
    if (element instanceof MyDiffNode) {
      MyDiffNode myDiffNode = ((MyDiffNode) (element));
      org.eclipse.compare.internal.patch.Diff diff = myDiffNode.getDiff();
      org.eclipse.compare.internal.patch.Hunk hunk = myDiffNode.getHunk();
      if (diff != null) {
        switch (diff.getType()) {
          case org.eclipse.compare.structuremergeviewer.Differencer.ADDITION:
            return getImageFor(
                add + (diff.fMatches ? "" : error), image, diff.fMatches); // $NON-NLS-1$

          case org.eclipse.compare.structuremergeviewer.Differencer.DELETION:
            return getImageFor(
                delete + (diff.fMatches ? "" : error), image, diff.fMatches); // $NON-NLS-1$

          default:
            return getImageFor(diff.fMatches ? "" : error, image, diff.fMatches); // $NON-NLS-1$
        }
      } else if (hunk != null) {
        return getImageFor(hunk.fMatches ? "" : error, image, hunk.fMatches); // $NON-NLS-1$
      }
    }
    return null;
  }
}
