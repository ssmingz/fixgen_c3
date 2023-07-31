class PlaceHold {
  public void setSegments(int[] segments) {
    checkLayout();
    if ((this.segments == null) && (segments == null)) {
      return;
    }
    if ((this.segments != null) && (segments != null)) {
      if (this.segments.length == segments.length) {
        int i;
        for (i = 0; i < segments.length; i++) {
          if (this.segments[i] != segments[i]) {
            break;
          }
        }
        if (i == segments.length) {
          return;
        }
      }
    }
    freeRuns();
    this.segments = segments;
  }
}
