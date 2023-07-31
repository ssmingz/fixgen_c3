class PlaceHold {
  int getItemCountTruncated(Rectangle rectangle) {
    int itemHeight = getItemHeight();
    int itemCount = 0;
    int startIndex;
    startIndex = rectangle.y / itemHeight;
    itemCount =
        ((int) (Compatibility.ceil((((float) (rectangle.y)) + rectangle.height) / itemHeight)))
            - startIndex;
    return itemCount;
  }
}
