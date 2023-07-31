class PlaceHold {
  int getRightItemEdge() {
    return (((((getSize().x - borderRight) - minRect.width) - maxRect.width) - topRightRect.width)
            - chevronRect.width)
        - 1;
  }
}
