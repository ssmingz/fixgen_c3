class PlaceHold {
  public int add(Image image) {
    int index = 0;
    while (index < images.length) {
      if (images[index] != null) {
        if (images[index].isDisposed()) {
          OS.g_object_unref(pixbufs[index]);
          images[index] = null;
          pixbufs[index] = 0;
        }
      }
      if (images[index] == null) {
        break;
      }
      index++;
    }
    if (index == images.length) {
      Image[] newImages = new Image[images.length + 4];
      System.arraycopy(images, 0, newImages, 0, images.length);
      images = newImages;
      long[] newPixbufs = new long[pixbufs.length + 4];
      System.arraycopy(pixbufs, 0, newPixbufs, 0, pixbufs.length);
      pixbufs = newPixbufs;
    }
    set(index, image);
    return index;
  }
}
