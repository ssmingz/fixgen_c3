class PlaceHold{
public float getWidth() {
    float width = 0.0F;
    int percIndex = widthStr.indexOf('%');
    if (percIndex > 0) {
        width = Float.parseFloat(widthStr.substring(0, percIndex));
        xPercent = true;
        return width / HUNDRED;
    } else {
        xPercent = false;
        return Float.parseFloat(widthStr);
    }
}
}