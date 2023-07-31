class PlaceHold {
  static void destroyImage(int image) {
    if (image == 0) {
      return;
    }
    PhImage_t phImage = new PhImage_t();
    OS.memmove(phImage, image, sizeof);
    phImage.flags = ((byte) (OS.Ph_RELEASE_IMAGE_ALL));
    OS.memmove(image, phImage, sizeof);
    OS.PhReleaseImage(image);
    OS.free(image);
  }
}
