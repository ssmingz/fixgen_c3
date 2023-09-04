class PlaceHold {
  public Image decorateImage(Image image, Object element) {

    if (element instanceof Diff) {
      Diff diff = (Diff) element;
      switch (diff.getType()) {
        case Differencer.ADDITION:
          return getImageFor(
              add + (diff.fMatches ? "" : error), image, diff.fMatches); // $NON-NLS-1$

        case Differencer.DELETION:
          return getImageFor(
              delete + (diff.fMatches ? "" : error), image, diff.fMatches); // $NON-NLS-1$

        default:
          return getImageFor(diff.fMatches ? "" : error, image, diff.fMatches); // $NON-NLS-1$
      }
    } else if (element instanceof Hunk) {
      Hunk hunk = (Hunk) element;
      return getImageFor((hunk.fMatches ? "" : error), image, hunk.fMatches); // $NON-NLS-1$
    }

    return null;
  }
}
