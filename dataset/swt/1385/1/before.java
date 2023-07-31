class PlaceHold {
  int createMask(int pixbuf) {
    int[] unused = new int[1];
    int[] width = new int[1];
    int[] height = new int[1];
    OS.XGetGeometry(xDisplay, pixbuf, unused, unused, unused, width, height, unused, unused);
    int mask = OS.XCreatePixmap(xDisplay, pixbuf, width[0], height[0], 1);
    int gc = OS.XCreateGC(xDisplay, mask, 0, null);
    OS.XCopyPlane(xDisplay, pixbuf, mask, gc, 0, 0, width[0], height[0], 0, 0, 1);
    OS.XFreeGC(xDisplay, gc);
    return mask;
  }
}
