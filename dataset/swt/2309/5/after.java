class PlaceHold {
  protected String shortenText(GC gc, String t, int width) {
    if (t == null) {
      return null;
    }
    int w = gc.textExtent(ellipsis).x;
    int l = t.length();
    int pivot = l / 2;
    int s = pivot;
    int e = pivot + 1;
    while ((s >= 0) && (e < l)) {
      String s1 = t.substring(0, s);
      String s2 = t.substring(e, l);
      int l1 = gc.textExtent(s1).x;
      int l2 = gc.textExtent(s2).x;
      if (((l1 + w) + l2) < width) {
        t = (s1 + ellipsis) + s2;
        break;
      }
      s--;
      e++;
    }
    return t;
  }
}
