class PlaceHold{
public float getHeight() {
    int percIndex = heightStr.indexOf('%');
    if (percIndex > 0) {
        float height = Float.parseFloat(heightStr.substring(0, percIndex));
        yPercent = true;
        return height / HUNDRED;
    } else {
        yPercent = false;
        return Float.parseFloat(heightStr);
    }
}
}