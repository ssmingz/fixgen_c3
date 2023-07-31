class PlaceHold {
  void onPaint(GC gc) {
    if ((left == null) && (right == null)) {
      return;
    }
    Point size = getSize();
    Color border1 = getDisplay().getSystemColor(BORDER1);
    if (bottom != null) {
      int y = (bottom.getBounds().y - BORDER_STRIPE) - 1;
      gc.setForeground(border1);
      gc.drawLine(0, y, size.x, y);
    }
    if ((left == null) || (right == null)) {
      return;
    }
    int[] line1 = new int[curve.length + 6];
    int index = 0;
    int x = curveStart;
    line1[index++] = x + 1;
    line1[index++] = size.y - BORDER_STRIPE;
    for (int i = 0; i < (curve.length / 2); i++) {
      line1[index++] = x + curve[2 * i];
      line1[index++] = curve[(2 * i) + 1];
    }
    line1[index++] = x + curve_width;
    line1[index++] = 0;
    line1[index++] = size.x;
    line1[index++] = 0;
    Color background = getBackground();
    if (getDisplay().getDepth() >= 15) {
      int[] line2 = new int[line1.length];
      index = 0;
      for (int i = 0; i < (line1.length / 2); i++) {
        line2[index] = line1[index++] - 1;
        line2[index] = line1[index++];
      }
      int[] line3 = new int[line1.length];
      index = 0;
      for (int i = 0; i < (line1.length / 2); i++) {
        line3[index] = line1[index++] + 1;
        line3[index] = line1[index++];
      }
      RGB from = border1.getRGB();
      RGB to = background.getRGB();
      int red = from.red + ((3 * (to.red - from.red)) / 4);
      int green = from.green + ((3 * (to.green - from.green)) / 4);
      int blue = from.blue + ((3 * (to.blue - from.blue)) / 4);
      Color color = new Color(getDisplay(), red, green, blue);
      gc.setForeground(color);
      gc.drawPolyline(line2);
      gc.drawPolyline(line3);
      color.dispose();
      int x1 = Math.max(0, curveStart - CURVE_TAIL);
      gc.setForeground(background);
      gc.setBackground(border1);
      gc.fillGradientRectangle(x1, size.y - BORDER_STRIPE, (curveStart - x1) + 1, 1, false);
    } else {
      int x1 = Math.max(0, curveStart - CURVE_TAIL);
      gc.setForeground(border1);
      gc.drawLine(x1, size.y - BORDER_STRIPE, curveStart + 1, size.y - BORDER_STRIPE);
    }
    gc.setForeground(border1);
    gc.drawPolyline(line1);
  }
}
