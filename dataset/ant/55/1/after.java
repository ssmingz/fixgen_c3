class PlaceHold{
public void setShortMonthNamesConfig(String shortMonthNames) {
    if ((shortMonthNames != null) && (!shortMonthNames.equals(""))) {
        this.shortMonthNamesConfig = shortMonthNames;
        configurationHasBeenSet();
    }
}
}