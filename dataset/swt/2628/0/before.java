class PlaceHold {
  Event getSegments(String string) {
    Event event = null;
    if (hooks(Segments) || filters(Segments)) {
      event = new Event();
      event.text = string;
      sendEvent(Segments, event);
      if ((event != null) && (event.segments != null)) {
        for (int i = 1,
                segmentCount = event.segments.length,
                lineLength = (string == null) ? 0 : string.length();
            i < segmentCount;
            i++) {
          if ((event.segments[i] < event.segments[i - 1]) || (event.segments[i] > lineLength)) {
            SWT.error(ERROR_INVALID_ARGUMENT);
          }
        }
      }
    }
    if ((state & HAS_AUTO_DIRECTION) != 0) {
      int direction = resolveTextDirection(string);
      if (direction == SWT.NONE) {
        direction = ((style & SWT.RIGHT_TO_LEFT) != 0) ? SWT.RIGHT_TO_LEFT : SWT.LEFT_TO_RIGHT;
      }
      int[] oldSegments = null;
      char[] oldSegmentsChars = null;
      if (event == null) {
        event = new Event();
      } else {
        oldSegments = event.segments;
        oldSegmentsChars = event.segmentsChars;
      }
      int nSegments = (oldSegments == null) ? 0 : oldSegments.length;
      event.segments = new int[nSegments + 1];
      event.segmentsChars = new char[nSegments + 1];
      if (oldSegments != null) {
        System.arraycopy(oldSegments, 0, event.segments, 1, nSegments);
      }
      if (oldSegmentsChars != null) {
        System.arraycopy(oldSegmentsChars, 0, event.segmentsChars, 1, nSegments);
      }
      event.segments[0] = 0;
      event.segmentsChars[0] = (direction == SWT.RIGHT_TO_LEFT) ? RLE : LRE;
    }
    return event;
  }
}
