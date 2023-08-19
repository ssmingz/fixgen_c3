class PlaceHold {
  private void addNewRange(char type, Object input, org.eclipse.jface.text.Position range) {
    switch (type) {
      case 'A':
        fNewAncestorRanges.put(input, range);
        break;
      case 'L':
        fNewLeftRanges.put(input, range);
        break;
      case 'R':
        fNewRightRanges.put(input, range);
        break;
    }
  }
}
