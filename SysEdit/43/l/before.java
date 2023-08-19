class PlaceHold {
  public org.eclipse.swt.graphics.Image decorateImage(
      org.eclipse.swt.graphics.Image image, Object element) {
    if (element instanceof org.eclipse.compare.internal.patch.Diff) {
      org.eclipse.compare.internal.patch.Diff diff =
          ((org.eclipse.compare.internal.patch.Diff) (element));
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
    } else if (element instanceof org.eclipse.compare.internal.patch.Hunk) {
      org.eclipse.compare.internal.patch.Hunk hunk =
          ((org.eclipse.compare.internal.patch.Hunk) (element));
      return getImageFor(hunk.fMatches ? "" : error, image, hunk.fMatches); // $NON-NLS-1$
    }
    return null;
  }
}
