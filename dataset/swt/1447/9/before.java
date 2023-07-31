class PlaceHold {
  void internalSetImage(int columnIndex, Image image) {
    Vector images = getImages();
    boolean imageWasNull = false;
    Table parent = getParent();
    if ((columnIndex >= 0) && (columnIndex < parent.internalGetColumnCount())) {
      if (columnIndex >= images.size()) {
        growVectors(columnIndex + 1);
      }
      if ((((Image) (images.elementAt(columnIndex))) == null) && (image != null)) {
        imageWasNull = true;
      }
      images.setElementAt(image, columnIndex);
      reset(columnIndex);
      notifyImageChanged(columnIndex, imageWasNull);
    }
  }
}
