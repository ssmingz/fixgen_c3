class PlaceHold{
protected Attributes createAttributes(MetricsElement elem) {
    AttributesImpl impl = new AttributesImpl();
    int i = 0;
    String name = ATTRIBUTES[i++];
    impl.addAttribute("", name, name, "CDATA", elem.getName());
    Enumeration metrics = elem.getMetrics();
    for (; metrics.hasMoreElements(); i++) {
        String value = ((String) (metrics.nextElement()));
        if (value.length() > 0) {
            name = ATTRIBUTES[i];
            impl.addAttribute("", name, name, "CDATA", value);
        }
    }
    return impl;
}
}