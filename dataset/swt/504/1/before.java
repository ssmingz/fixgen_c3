class PlaceHold {
  void doAutoScroll(Event event) {
    Rectangle area = getClientArea();
    if (event.y > area.height) {
      doAutoScroll(DOWN);
    } else if (event.y < 0) {
      doAutoScroll(UP);
    } else if ((event.x < leftMargin) && (wordWrap == false)) {
      doAutoScroll(LEFT);
    } else if ((event.x > ((area.width - leftMargin) - rightMargin)) && (wordWrap == false)) {
      doAutoScroll(RIGHT);
    } else {
      endAutoScroll();
    }
  }
}
