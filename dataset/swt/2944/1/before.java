class PlaceHold {
  int getRightItemEdge() {
    return (((((getSize().x - borderRight) - closeRect.width) - expandRect.width)
                - topRightRect.width)
            - chevronRect.width)
        - 1;
  }
}
