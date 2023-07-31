class PlaceHold {
  private void checkThrowsTags(List aTags, List aThrows) {
    final Set foundThrows = new HashSet();
    final ListIterator tagIt = aTags.listIterator();
    while (tagIt.hasNext()) {
      final JavadocTag tag = ((JavadocTag) (tagIt.next()));
      if (!tag.isThrowsTag()) {
        continue;
      }
      tagIt.remove();
      final String documentedEx = tag.getArg1();
      boolean found = foundThrows.contains(documentedEx);
      final ListIterator throwIt = aThrows.listIterator();
      while ((!found) && throwIt.hasNext()) {
        final LineText t = ((LineText) (throwIt.next()));
        if (t.getText().equals(documentedEx)) {
          found = true;
          throwIt.remove();
          foundThrows.add(documentedEx);
        }
      }
      if (!found) {
        log(tag.getLineNo(), ("Unused @throws tag for '" + tag.getArg1()) + "'.");
      }
    }
    final ListIterator throwIt = aThrows.listIterator();
    while (throwIt.hasNext()) {
      final LineText t = ((LineText) (throwIt.next()));
      log(t.getLineNo(), ("Expected @throws tag for '" + t.getText()) + "'.");
    }
  }
}
