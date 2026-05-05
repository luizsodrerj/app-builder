package webappbuilder.webappfaces.view.helper;

public class AppAttributeHelper {

    private String title;
    private String text;
    private String link;
    private String btLabel;
    private boolean hasParameter;
    private String paramName;
    private String param;
    private String icon;

    public AppAttributeHelper(
            String title,
            String text,
            String link,
            String btLabel,
            boolean hasParameter,
            String param,
            String paramName,
            String icon
    ) {
        this.title = title;
        this.text = text;
        this.link = link;
        this.btLabel = btLabel;
        this.hasParameter = hasParameter;
        this.paramName = paramName;
        this.param = param;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getBtLabel() {
        return btLabel;
    }

    public boolean isHasParameter() {
        return hasParameter;
    }

    public String getParam() {
        return param;
    }

    public String getIcon() {
        return icon;
    }

    public String getParamName() {
        return paramName;
    }
}
