class PlaceHold {
  public void notifyListeners(ImageLoaderEvent event) {
    if (!hasListeners()) {
      return;
    }
    int size = imageLoaderListeners.size();
    for (int i = 0; i < size; i++) {
      ImageLoaderListener listener = ((ImageLoaderListener) (imageLoaderListeners.elementAt(i)));
      listener.imageDataLoaded(event);
    }
  }
}
